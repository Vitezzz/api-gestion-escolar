package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


// ==================== USUARIO ====================
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_usuario")
    private Integer id;
    
    @Column(name = "username", nullable = false, unique = true, length = 80)
    private String username;
    
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    /*@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
    */

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude // <--- AGREGA ESTO para que no se cicle
    @Builder.Default  // <--- Útil si usas Builder
    private List<UsuarioRol> usuarioRoles = new ArrayList<>(); // <--- Usa ArrayList

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<HistorialEstadoEstudiante> historialCambios;
}