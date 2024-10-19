package com.udea.gestiondevuelos.util.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desactiva toda la seguridad para permitir todas las solicitudes
        // Desactiva CSRF si es necesario para pruebas
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}