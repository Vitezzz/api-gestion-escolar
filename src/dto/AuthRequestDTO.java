package com.escuela.gestion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTOs usando Records de Java 21
 * Los Records son inmutables y perfectos para DTOs
 */

// ==================== AUTH DTOs ====================

/**
 * Request para autenticación
 */
public record AuthRequestDTO(
    @NotBlank(message = "El username es obligatorio")
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    String password
) {}

/**
 * Response de autenticación exitosa
 */
public record AuthResponseDTO(
    String token,
    String refreshToken,
    String username,
    List<String> roles,
    Long expiresIn
) {}

/**
 * Request para registro de usuario
 */
public record RegisterRequestDTO(
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 80)
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", 
             message = "La contraseña debe contener mayúsculas, minúsculas y números")
    String password,
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    String email,
    
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,
    
    @NotNull(message = "El rol es obligatorio")
    Integer roleId
) {}
