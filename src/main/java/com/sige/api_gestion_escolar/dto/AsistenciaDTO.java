package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaDTO {
    private Integer id;
    
    @NotNull(message = "La sección es requerida")
    private Integer seccionId;
    
    @NotNull(message = "El estudiante es requerido")
    private Integer estudianteId;
    
    private String estudianteNombre;
    private String estudianteMatricula;
    
    @NotNull(message = "La fecha es requerida")
    private LocalDate fecha;
    
    @NotBlank(message = "El estado es requerido")
    private String estado;
    
    private String observaciones;
}
