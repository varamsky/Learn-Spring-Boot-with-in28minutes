package com.in28minutes.rest.webservices.restfulwebservices.user;


import com.in28minutes.rest.webservices.restfulwebservices.versioning.PersonV1;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
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
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

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
}
