package com.auth.auth_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/login").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Example in-memory users (optional)
    // @Bean
    // public UserDetailsService users() {
    //     UserDetails user1 = User.withUsername("spring")
    //         .password(passwordEncoder().encode("spring"))
    //         .roles("ADMIN")
    //         .build();
    //     UserDetails user2 = User.withUsername("l")
    //         .password(passwordEncoder().encode("l"))
    //         .roles("ADMIN")
    //         .build();
    //     return new InMemoryUserDetailsManager(user1, user2);
    // }
}
