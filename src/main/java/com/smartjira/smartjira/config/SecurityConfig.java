package com.smartjira.smartjira.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint, JwtAuthFilter jwtAuthFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/task/**").authenticated()
                        .requestMatchers("/api/v1/project/").authenticated()
                        .requestMatchers("/api/v1/manager/").authenticated()
                        .requestMatchers("/api/v1/task/").authenticated()
                        .requestMatchers("/api/v1/developer/").authenticated()
                        .requestMatchers("/api/v1/hr").authenticated()
                        .requestMatchers("/api/v1/leave/").authenticated()
                        .requestMatchers("/api/v1/salary/").authenticated()
                        .anyRequest().permitAll() // <-- all other endpoints are public
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}