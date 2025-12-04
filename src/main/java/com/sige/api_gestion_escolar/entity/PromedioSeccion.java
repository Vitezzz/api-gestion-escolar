package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


// ==================== PROMEDIO SECCION ====================
@Entity
@Table(name = "promedio_seccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromedioSeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_promedio")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_inscripcion", nullable = false)
    private Inscripcion inscripcion;

    @Column(name = "promedio", precision = 5, scale = 2)
    private BigDecimal promedio;

    @Column(name = "visto")
    private Boolean visto = false;
}
