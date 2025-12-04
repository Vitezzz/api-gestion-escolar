package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionDTO {
    private Integer id;
    
    @NotNull(message = "La sección es requerida")
    private Integer seccionId;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    private LocalDate fecha;
    
    @NotNull(message = "El porcentaje es requerido")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje debe ser menor o igual a 100")
    private BigDecimal porcentaje;
    
    private String tipo;
}