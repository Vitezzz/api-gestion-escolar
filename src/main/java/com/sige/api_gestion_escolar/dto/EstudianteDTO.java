package com.sige.api_gestion_escolar.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteDTO {
    private Integer id;
    
    @NotBlank(message = "La matrícula es requerida")
    private String matricula;
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    @NotBlank(message = "El apellido paterno es requerido")
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "El email es requerido")
    @Email(message = "Email inválido")
    private String email;
    
    private String telefono;
    
    @NotNull(message = "El programa es requerido")
    private Integer programaId;
    
    private String programaNombre;
    
    @NotNull(message = "La fecha de ingreso es requerida")
    private LocalDate fechaIngreso;
    
    private Integer cuatrimestreActual;
    private String status;
}

