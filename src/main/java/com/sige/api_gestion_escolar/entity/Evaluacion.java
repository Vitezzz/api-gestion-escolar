package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

// ==================== EVALUACION ====================
@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_evaluacion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_seccion", nullable = false)
    private Seccion seccion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "porcentaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "tipo", length = 20)
    private String tipo = "EXAMEN";

    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL)
    private List<Calificacion> calificacionesRegistradas;
}
