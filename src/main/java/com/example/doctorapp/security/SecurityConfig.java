package com.example.doctorapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for Postman testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // ✅ Allow register/login
                        .requestMatchers("/api/appointments/**").permitAll() // ✅ Allow public access for now
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ For CORS preflight
                        .anyRequest().authenticated() // ✅ Protect others
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // ✅ No session-based auth

        return http.build();
    }
}
