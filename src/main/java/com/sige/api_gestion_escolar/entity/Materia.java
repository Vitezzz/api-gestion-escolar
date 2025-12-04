
package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_materia")
    private Integer id;
    
    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;
    
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    
    @Column(name = "creditos", nullable = false)
    private Integer creditos;
    
    @Column(name = "horas_teoria")
    private Integer horasTeoria = 0;
    
    @Column(name = "horas_practica")
    private Integer horasPractica = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_departamento")
    private Departamento departamento;
    
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Seccion> secciones;
    
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<RequisitoMateria> requisitos;
}

