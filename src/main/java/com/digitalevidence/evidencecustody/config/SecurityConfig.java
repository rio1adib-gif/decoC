package com.digitalevidence.evidencecustody.config;

import com.digitalevidence.evidencecustody.security.JwtAuthenticationEntryPoint;
import com.digitalevidence.evidencecustody.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .authorizeHttpRequests(auth -> auth
                // Public Assets & JSP Views
                .requestMatchers("/", "/login", "/error", "/favicon.ico").permitAll()
                .requestMatchers("/assets/**", "/css/**", "/js/**").permitAll()
                .requestMatchers("/WEB-INF/views/**").permitAll() 
                
                // Page Routes (PermitAll because JS auth-guards handle redirects)
                .requestMatchers("/admin", "/officer", "/forensic", "/audit", "/reports").permitAll()

                // API Endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**", "/api/audit/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/cases/**").hasRole("OFFICER")
                .requestMatchers(HttpMethod.GET, "/api/cases/**").hasAnyRole("ADMIN", "OFFICER", "FORENSIC")
                .requestMatchers("/api/evidence/**").hasAnyRole("ADMIN", "OFFICER", "FORENSIC")
                
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}