package com.in28minutes.rest.webservices.restfulwebservices.user;


import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        /*
        Adding HATEOAS(Hypermedia as the Engine of Application State)

        We can send additional meta-links with the response of our API.
        For example,
        GET http://localhost:8080/users/1

        Response body:
        {
            "id": 1,
            "name": "Adam",
            "birthDate": "1993-07-31",
            "_links": {
                "all-users": {
                "href": "http://localhost:8080/users"
                }
            }
        }
         */
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        return user.get().getPosts();
    }

    @GetMapping("/jpa/users/{user_id}/posts/{post_id}")
    public Post retrievePostByIdForUser(@PathVariable int user_id, @PathVariable int post_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + user_id);

        return user.get().getPosts().stream().filter(post -> post.getId() == post_id).findFirst().get();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        /*
        This gets the current request path i.e, /users
        Then creates the /{id} path.
        Replaces {id} with the id of the newly saved user(for example, 4) leading to /4
        Then concatenates them to form the URI /users/4
         */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        /*
        With ResponseEntity.created() we send the CREATED 201 HTTP Response code.
        Sending in the URI location is not mandatory.
        The URI location is the URI of the newly created user(for example,http://localhost:8080/users/4).
        We can send in null and that will not send the location value.

        The build() is used when we want to send a ResponseEntity with an empty body.
        We can also replace it with body like -> ResponseEntity.created(location).body("ENTER BODY TEXT HERE");
         */
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        /*
         TODO: Why do we need to do this even after adding @Valid?
         I also tried to add @Validated at method level and replaced @Valid with @NotNull(as seen in udemy comments) but it didn't work
         */
        if(post.getDescription() == null)
            throw new IllegalArgumentException("Post cannot be empty");
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
