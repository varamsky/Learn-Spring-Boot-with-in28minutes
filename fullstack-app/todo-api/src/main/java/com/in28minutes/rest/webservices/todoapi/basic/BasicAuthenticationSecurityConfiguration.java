package com.in28minutes.rest.webservices.todoapi.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// This is commented out right now as we are using JWT configuration and, we don't want Spring to pick up this as a configuration file.
//@Configuration
public class BasicAuthenticationSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         - Filter chain
         - Authenticate all requests
         - Basic Authentication
         - Disabling CSRF
         - Stateless REST API

         Here, we are chaining the calls
         */
        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow http method options(pre-flight request) to work for all urls
                        .anyRequest().authenticated()) // All requests are authenticated
                .httpBasic(Customizer.withDefaults()) // Shows a popup and asks for credentials(useful when using REST API as form login is not handled in the backend)
                // TODO: Why do we do this?
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless session
                // TODO: Why do we disable this?
                .csrf((AbstractHttpConfigurer::disable)) // disabled CSRF
                .build(); // return the build()
    }

}
