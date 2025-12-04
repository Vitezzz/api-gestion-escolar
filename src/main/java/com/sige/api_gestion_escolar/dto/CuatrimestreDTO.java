package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuatrimestreDTO {
    private Integer id;
    
    @NotBlank(message = "El período es requerido")
    private String periodo;
    
    @NotNull(message = "El año es requerido")
    private Integer anio;
    
    @NotNull(message = "El número de cuatrimestre es requerido")
    @Min(value = 1, message = "El número debe ser entre 1 y 3")
    @Max(value = 3, message = "El número debe ser entre 1 y 3")
    private Integer numeroCuatrimestre;
    
    @NotNull(message = "La fecha de inicio es requerida")
    private LocalDate fechaInicio;
    
    @NotNull(message = "La fecha fin es requerida")
    private LocalDate fechaFin;
    
    private Boolean activo;
}