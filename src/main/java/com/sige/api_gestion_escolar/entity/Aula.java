package com.sige.api_gestion_escolar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "aula")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_aula")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    
    @Column(name = "edificio", length = 80)
    private String edificio;
    
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL)
    private List<Seccion> secciones;
}