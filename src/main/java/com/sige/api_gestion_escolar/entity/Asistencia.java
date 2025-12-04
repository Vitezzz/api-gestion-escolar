package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


// ==================== ASISTENCIA ====================
@Entity
@Table(name = "asistencia", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cve_seccion", "cve_estudiante", "fecha"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_asistencia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_seccion", nullable = false)
    private Seccion seccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_estudiante", nullable = false)
    private Estudiante estudiante;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", length = 20)
    private String estado = "PRESENTE";

    @Column(name = "observaciones", length = 250)
    private String observaciones;
}
