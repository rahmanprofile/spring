package com.rahman.learning.util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Disable default user
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();  // No users by default
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users").permitAll()  // Allow public registration
                        .anyRequest().authenticated()           // All other requests require auth
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
