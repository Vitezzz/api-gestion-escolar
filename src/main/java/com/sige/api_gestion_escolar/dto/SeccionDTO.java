package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeccionDTO {
    private Integer id;
    
    @NotNull(message = "La materia es requerida")
    private Integer materiaId;
    
    private String materiaNombre;
    private String materiaCodigo;
    
    @NotNull(message = "El cuatrimestre es requerido")
    private Integer cuatrimestreId;
    
    private String cuatrimestrePeriodo;
    
    @NotBlank(message = "El grupo es requerido")
    private String grupo;
    
    private Integer docenteId;
    private String docenteNombre;
    
    private Integer aulaId;
    private String aulaNombre;
    
    @NotNull(message = "El máximo de alumnos es requerido")
    @Positive(message = "El máximo debe ser positivo")
    private Integer maxAlumnos;
    
    private Integer inscritos;
    private String diaHorario;
    private String tipo;
    private String estado;
}
