package com.sige.api_gestion_escolar.controller;

import com.sige.api_gestion_escolar.dto.*;
import com.sige.api_gestion_escolar.entity.*;
import com.sige.api_gestion_escolar.repository.*;
import com.sige.api_gestion_escolar.security.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints de autenticación y registro")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toSet());
            
            Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            JwtResponse response = JwtResponse.builder()
                    .token(jwt)
                    .id(usuario.getId())
                    .username(usuario.getUsername())
                    .email(usuario.getEmail())
                    .roles(roles)
                    .build();
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(MessageResponse.error("Credenciales inválidas"));
        }
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroRequest registroRequest) {
        if (usuarioRepository.existsByUsername(registroRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(MessageResponse.error("El username ya existe"));
        }
        
        if (usuarioRepository.existsByEmail(registroRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(MessageResponse.error("El email ya existe"));
        }
        
        Usuario usuario = Usuario.builder()
                .username(registroRequest.getUsername())
                .email(registroRequest.getEmail())
                .passwordHash(passwordEncoder.encode(registroRequest.getPassword()))
                .nombre(registroRequest.getNombre())
                .activo(true)
                .build();
        
        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        
        if (registroRequest.getRoles() == null || registroRequest.getRoles().isEmpty()) {
            Rol rolEstudiante = rolRepository.findByNombre("ESTUDIANTE")
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            UsuarioRol usuarioRol = UsuarioRol.builder()
                    .usuario(usuario)
                    .rol(rolEstudiante)
                    .build();
            usuarioRoles.add(usuarioRol);
        } else {
            for (String rolNombre : registroRequest.getRoles()) {
                Rol rol = rolRepository.findByNombre(rolNombre)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolNombre));
                UsuarioRol usuarioRol = UsuarioRol.builder()
                        .usuario(usuario)
                        .rol(rol)
                        .build();
                usuarioRoles.add(usuarioRol);
            }
        }
        
        usuario.setUsuarioRoles(new ArrayList<>(usuarioRoles));
        usuarioRepository.save(usuario);
        
        return ResponseEntity.ok(MessageResponse.success("Usuario registrado exitosamente"));
    }

   
}

