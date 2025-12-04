package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramaDTO {
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    @NotBlank(message = "La clave es requerida")
    private String clave;
    
    @NotNull(message = "La duración en cuatrimestres es requerida")
    @Positive(message = "La duración debe ser positiva")
    private Integer duracionCuatrimestres;
    
    private String descripcion;
    private Boolean activo;
}

