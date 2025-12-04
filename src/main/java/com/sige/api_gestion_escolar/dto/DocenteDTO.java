package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocenteDTO {
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    @NotBlank(message = "El apellido paterno es requerido")
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    @NotBlank(message = "El email es requerido")
    @Email(message = "Email inválido")
    private String email;
    
    private String telefono;
    private Integer departamentoId;
    private String departamentoNombre;
    private Boolean activo;
}

