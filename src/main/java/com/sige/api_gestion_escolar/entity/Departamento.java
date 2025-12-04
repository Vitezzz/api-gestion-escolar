package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_departamento")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;
    
    @Column(name = "codigo", unique = true, length = 20)
    private String codigo;
    
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad")
    private UnidadAcademica unidadAcademica;
    
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Docente> docentes;
    
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Materia> materias;
}
