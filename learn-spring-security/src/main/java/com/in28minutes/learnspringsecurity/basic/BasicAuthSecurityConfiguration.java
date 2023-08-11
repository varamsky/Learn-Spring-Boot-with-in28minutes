package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


enum ROLES {
    USER, ADMIN
}

@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**") // allow all mappings to be accessible
                        .allowedMethods("*") // allow all methods to be accessible
                        .allowedOrigins("http://localhost:3000"); // allow the accessibility for these origins
            }
        };
    }

    /*
    This adds In-memory users.

    This has been removed because we configure to store and use users from the database below.
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("in28minutes")
//                .password("{noop}dummy")
//                .roles(String.valueOf(ROLES.USER))
//                .build();// As we are not using any encryption for our password we have added the prefix {noop}
//
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("{noop}dummy")
//                .roles(String.valueOf(ROLES.ADMIN))
//                .build();// As we are not using any encryption for our password we have added the prefix {noop}
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public DataSource dataSource() {
        /*
        Instead of creating a SQL files for creating users table ourselves we can use the default users table schema.
        This creates 2 tables:
        1. users(username, password, enabled)
        2. authorities(username, authority)
         */
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails user = User
                .withUsername("in28minutes")
//                .password("{noop}dummy")
                .password("dummy").passwordEncoder(str -> passwordEncoder().encode(str)) // This hashes the password before it is stored in the database
                .roles(String.valueOf(ROLES.USER))
                .build();// As we are not using any encryption for our password we have added the prefix {noop}

        UserDetails admin = User
                .withUsername("admin")
//                .password("{noop}dummy")
                .password("dummy").passwordEncoder(str -> passwordEncoder().encode(str)) // This hashes the password before it is stored in the database
                .roles(String.valueOf(ROLES.ADMIN), String.valueOf(ROLES.USER))
                .build();// As we are not using any encryption for our password we have added the prefix {noop}

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
