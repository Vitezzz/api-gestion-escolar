package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalificacionDTO {
    private Integer id;
    private Integer inscripcionId;
    private Integer evaluacionId;
    private String evaluacionNombre;
    
    @DecimalMin(value = "0.0", message = "La calificación debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "La calificación debe ser menor o igual a 100")
    private BigDecimal calificacion;
    
    private LocalDateTime fechaRegistro;
    private String observaciones;
}
