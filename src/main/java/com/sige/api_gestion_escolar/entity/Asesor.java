package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


// ==================== ASESOR ====================
@Entity
@Table(name = "asesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_asesor")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_docente", nullable = false)
    private Docente docente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_estudiante", nullable = false)
    private Estudiante estudiante;
    
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;
}
