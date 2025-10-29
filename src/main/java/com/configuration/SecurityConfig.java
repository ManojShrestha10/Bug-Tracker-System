package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/developer/**").hasRole("DEVELOPER")
                .requestMatchers("/tester/**").hasRole("TESTER")
                .anyRequest().permitAll());
        // login configuration
        http.formLogin((form) -> form
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .successForwardUrl("/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/login?error=true")
                .permitAll());
        // logout configutation
        http.logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll());
        // return the http security filter chain
        return http.build();
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
