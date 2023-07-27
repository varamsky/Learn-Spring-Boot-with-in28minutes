package com.in28minutes.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    /*
    Typically, whenever you need to store usernames and passwords you will make use of LDAP or at least a Database
    Here, to keep things simple we will use In Memory Configurer.

    Now, just by adding this we can use this to login into our application using our custom username and password.
     */
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
        UserDetails userDetails2 = createNewUser("ranga", "dummydummy");

        // InMemoryUserDetailsManager constructor accepts a varargs(variable arguments) of UserDetails so, we can send one or many UserDetails to the constructor
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        /*
        This is a simple lambda expression which takes in a String and returns back a String.
        In between, we use the passwordEncoder() method, which uses BCryptPasswordEncoder to encrypt our input and returns the encrypted data.
         */
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        /*
        If you look at the User, UserDetails and UserBuilder(inner class of User class) classes all of this will make sense.
        The builder() method and the final build() method. all will make sense.
         */
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", "ADMIN").build();
        return userDetails;
    }

    /*
    Just adding this method means that Spring will start using this password encoder.
    Therefore, only doing this will not work as when entering the username and password in the browser Spring will try to decode it with this passwordEncoder and fail as the simple(non-encoded) string that we entered and decoded value by the encoder will not match.
    We need to encode our credentials with this password encoder and the same is done in the createUserDetailsManager() method.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // We make sure that all urls are protected and need authentication
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // We allow the login form to be shown to the unauthorised users
        http.formLogin(Customizer.withDefaults());
        /*
        For H2 to work, we need to disable csrf and enable frames

        These methods are deprecated so, we used the lambda expression alternatives instead:-
        http.csrf().disable();
        http.headers().frameOptions().disable();
         */
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::disable)));

        return http.build();
    }
}
