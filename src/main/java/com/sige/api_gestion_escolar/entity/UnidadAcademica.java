package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "unidad_academica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_unidad")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    
    @Column(name = "direccion", length = 250)
    private String direccion;
    
    @Column(name = "telefono", length = 30)
    private String telefono;
    
    @OneToMany(mappedBy = "unidadAcademica", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;
}
