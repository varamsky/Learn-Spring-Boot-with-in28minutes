package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        /*
        Unlike the myfirstwebapp application, here we are not using http.formLogin(Customizer.withDefaults()) because we don't want this to be an MVC app.
        This is just a REST API so the form page will be built on the frontend side.

        With this we can send credentials with the Authentication headers when using the API.
         */
        http.httpBasic(Customizer.withDefaults()); // This will show a popup and ask for credentials
        http.csrf(AbstractHttpConfigurer::disable); // Without this you cannot do POST/PUT operations

        return http.build();
    }
}
