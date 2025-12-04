package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

// ==================== SECCION ====================
@Entity
@Table(name = "seccion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cve_materia", "cve_cuatrimestre", "grupo"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_seccion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_materia", nullable = false)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_cuatrimestre", nullable = false)
    private Cuatrimestre cuatrimestre;

    @Column(name = "grupo", nullable = false, length = 10)
    private String grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_docente")
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_aula")
    private Aula aula;

    @Column(name = "max_alumnos", nullable = false)
    private Integer maxAlumnos;

    @Column(name = "dia_horario", length = 80)
    private String diaHorario;

    @Column(name = "tipo", length = 20)
    private String tipo = "REGULAR";

    @Column(name = "estado", length = 20)
    private String estado = "ABIERTA";

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    private List<Evaluacion> evaluaciones;

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;
}