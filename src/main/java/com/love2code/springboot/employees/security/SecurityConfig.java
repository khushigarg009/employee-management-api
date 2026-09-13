package com.love2code.springboot.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // ---------------------------------------------------
    // 1. Password Encoder — REQUIRED. Without this,
    //    Spring Security cannot match raw/stored passwords
    //    and authentication will always fail.
    // ---------------------------------------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ---------------------------------------------------
    // 2. JDBC-backed user details (loads users + roles from DB)
    // ---------------------------------------------------
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, password, active from system_users where user_id = ?"
        );

        // Query to retrieve authorities/roles by username
        // NOTE: the "role" column values MUST be prefixed with "ROLE_"
        // e.g. ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_ADMIN
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?"
        );

        return jdbcUserDetailsManager;
    }

    // ---------------------------------------------------
    // 3. Security Filter Chain
    // ---------------------------------------------------
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // --- Authorization rules ---
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
                        .requestMatchers("/docs/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // --- Enable HTTP Basic Auth (only ONE call — do not disable then enable) ---
                .httpBasic(Customizer.withDefaults())

                // --- Disable CSRF (fine for a stateless REST API) ---
                .csrf(AbstractHttpConfigurer::disable)

                // --- Custom 401 handler (returns JSON instead of browser popup) ---
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint()))

                // --- Allow H2 console to render in a frame ---
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    // ---------------------------------------------------
    // 4. Custom entry point for unauthenticated requests
    // ---------------------------------------------------
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setHeader("WWW-Authenticate", ""); // prevents browser auth popup
            response.getWriter().write("{\"error\" : \"Unauthorized Access\"}");
        };
    }
}