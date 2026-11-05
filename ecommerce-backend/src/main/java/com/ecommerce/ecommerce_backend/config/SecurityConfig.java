package com.ecommerce.ecommerce_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF disable
                .authorizeHttpRequests(auth -> auth
                        // 1. Status update wali API ko sirf ADMIN role ke liye allow karein
                        .requestMatchers("/api/orders/*/status").hasRole("ADMIN")

                        // 2. Baki saari APIs ko sabke liye open rakhein
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()); // Basic Auth enable karein

        return http.build();
    }
    }