package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

// ==================== ESTUDIANTE ====================
@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_estudiante")
    private Integer id;

    @Column(name = "matricula", nullable = false, unique = true, length = 30)
    private String matricula;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "apellido_p", nullable = false, length = 80)
    private String apellidoPaterno;

    @Column(name = "apellido_m", length = 80)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_programa")
    private Programa programa;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "cuatrimestre_actual")
    private Integer cuatrimestreActual = 1;

    @Column(name = "status", length = 25)
    private String status = "ACTIVO";

    // Relaciones hacia otras tablas (Inscripciones, Asistencias, Pagos, etc.)
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Asesor> asesores;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<HistorialEstadoEstudiante> historialEstados;
}