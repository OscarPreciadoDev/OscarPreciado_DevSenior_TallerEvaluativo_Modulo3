package com.vetturno.vetturno.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http


                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Deja públicos únicamente Registro y Login ("api/auth/")
                        .requestMatchers("/api/auth/**").permitAll()

                        // Permite el acceso a las rutas técnicas de swagger sin abrir los endpoints de negocio
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs").permitAll()

                        // Permite el trabajo de recepción a USER y ADMIN
                        .requestMatchers
                                ( "/api/citas/**",
                                "/api/veterinarios/listar/**",
                                        "/api/mascotas/**",
                                        "/api/propietarios/**").hasAnyRole("ADMIN","USER")

                        // Restringe POST /api/veteriarios a ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/veterinarios/crear").hasRole("ADMIN")

                        // Estas rutas necesitan autenticación
                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}

