package com.example.spring_mvc_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/signup","/api/login","api/member","api/posts","api/posts/{postId}","api/test").permitAll()
                                .requestMatchers("/error").permitAll()      // allow error endpoint without auth
                                .anyRequest().authenticated()


                )
                .httpBasic(httpBasic -> {});  // Enable HTTP Basic Auth with default settings for debugging

        return http.build();
    }







}
