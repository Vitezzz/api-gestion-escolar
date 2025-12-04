package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

// ==================== INSCRIPCION ====================
@Entity
@Table(name = "inscripcion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cve_estudiante", "cve_seccion"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_inscripcion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_seccion", nullable = false)
    private Seccion seccion;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    @Column(name = "condicion", length = 20)
    private String condicion = "REGULAR";

    @Column(name = "estado", length = 20)
    private String estado = "ACTIVA";

    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToOne(mappedBy = "inscripcion", cascade = CascadeType.ALL)
    private PromedioSeccion promedioSeccion;

    @PrePersist
    protected void onCreate() {
        if (fechaInscripcion == null) {
            fechaInscripcion = LocalDateTime.now();
        }
    }
}