package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "historial_estado_estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialEstadoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_historial")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_estudiante", nullable = false)
    private Estudiante estudiante;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @Column(name = "estado_anterior", length = 60)
    private String estadoAnterior;
    
    @Column(name = "estado_nuevo", nullable = false, length = 60)
    private String estadoNuevo;
    
    @Column(name = "comentario", length = 250)
    private String comentario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_usuario")
    private Usuario usuario;
    
    @PrePersist
    protected void onCreate() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }
}