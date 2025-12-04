package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDTO {
    private Integer id;
    
    @NotBlank(message = "El código es requerido")
    private String codigo;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    @NotNull(message = "Los créditos son requeridos")
    @Positive(message = "Los créditos deben ser positivos")
    private Integer creditos;
    
    private Integer horasTeoria;
    private Integer horasPractica;
    private Integer departamentoId;
    private String departamentoNombre;
    private String descripcion;
    private Boolean activo;
}