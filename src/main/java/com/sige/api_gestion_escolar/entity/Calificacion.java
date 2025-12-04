package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;


// ==================== CALIFICACION ====================
@Entity
@Table(name = "calificacion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cve_inscripcion", "cve_evaluacion"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_calificacion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_inscripcion", nullable = false)
    private Inscripcion inscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    @Column(name = "calificacion", precision = 5, scale = 2)
    private BigDecimal valorCalificacion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "observaciones", length = 250)
    private String observaciones;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
    }
}