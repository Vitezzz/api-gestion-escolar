package com.sige.api_gestion_escolar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    private final AuthTokenFilter authTokenFilter;
    private final UserDetailsService userDetailsService;
    
    public SecurityConfig(AuthTokenFilter authTokenFilter, UserDetailsService userDetailsService) {
        this.authTokenFilter = authTokenFilter;
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 1. Lo público de siempre
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                
                // 2. Vistas HTML (Permitir todo)
                .requestMatchers(
                    "/", "/login", "/index", 
                    "/dashboard", "/estudiantes", "/docentes", 
                    "/materias", "/programas", "/cuatrimestres", "/secciones"
                ).permitAll()

                // 3. !!! EL TRUCO: Permitir la API de materias SIN LOGIN !!!
                // Esto evitará que te saque, pase lo que pase
                .requestMatchers("/api/materias/**").permitAll()  // <--- AGREGA ESTA LÍNEA
                
                // 4. El resto de la API sigue protegida
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
            );
        
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}