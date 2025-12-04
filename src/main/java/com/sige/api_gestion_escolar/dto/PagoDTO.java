package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoDTO {
    private Integer id;
    
    @NotNull(message = "El estudiante es requerido")
    private Integer estudianteId;
    
    private String estudianteNombre;
    private LocalDateTime fecha;
    
    @NotNull(message = "El monto es requerido")
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal monto;
    
    @NotBlank(message = "El concepto es requerido")
    private String concepto;
    
    private String metodo;
}
