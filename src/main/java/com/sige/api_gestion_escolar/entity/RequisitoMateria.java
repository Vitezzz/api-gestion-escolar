package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "requisito_materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequisitoMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_req")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_materia", nullable = false)
    private Materia materia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_materia_prerreq", nullable = false)
    private Materia materiaPrerrequisito;
    
    @Column(name = "tipo", length = 20)
    private String tipo = "PREREQUISITO";
}