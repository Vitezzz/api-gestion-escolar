package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO {
    private Integer id;
    
    @NotNull(message = "El estudiante es requerido")
    private Integer estudianteId;
    
    private String estudianteNombre;
    private String estudianteMatricula;
    
    @NotNull(message = "La sección es requerida")
    private Integer seccionId;
    
    private String materiaNombre;
    private String grupo;
    private LocalDateTime fechaInscripcion;
    private String condicion;
    private String estado;
}

