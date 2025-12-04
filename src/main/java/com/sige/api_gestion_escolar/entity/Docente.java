package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "docente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_docente")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;
    
    @Column(name = "apellido_p", nullable = false, length = 80)
    private String apellidoPaterno;
    
    @Column(name = "apellido_m", length = 80)
    private String apellidoMaterno;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "telefono", length = 30)
    private String telefono;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_departamento")
    private Departamento departamento;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    private List<Seccion> secciones;
    
    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    private List<Asesor> asesorias;
}