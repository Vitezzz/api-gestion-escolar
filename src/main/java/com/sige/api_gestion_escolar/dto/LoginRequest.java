package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;


// ==================== AUTH DTOs ====================
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "El username es requerido")
    private String username;
    
    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
