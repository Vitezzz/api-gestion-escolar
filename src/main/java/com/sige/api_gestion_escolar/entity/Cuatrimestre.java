package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

// ==================== CUATRIMESTRE ====================
@Entity
@Table(name = "cuatrimestre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuatrimestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_cuatrimestre")
    private Integer id;
    
    @Column(name = "periodo", nullable = false, unique = true, length = 30)
    private String periodo;
    
    @Column(name = "anio", nullable = false)
    private Integer anio;
    
    @Column(name = "numero_cuatrimestre", nullable = false)
    private Integer numeroCuatrimestre;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "cuatrimestre", cascade = CascadeType.ALL)
    private List<Seccion> secciones;
}
