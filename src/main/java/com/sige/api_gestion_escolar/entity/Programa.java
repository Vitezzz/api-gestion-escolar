package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "programa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_programa")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    
    @Column(name = "clave", nullable = false, unique = true, length = 20)
    private String clave;
    
    @Column(name = "duracion_cuatrimestres", nullable = false)
    private Integer duracionCuatrimestres;
    
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;
}
