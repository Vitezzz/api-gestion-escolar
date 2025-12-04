package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

// ==================== ROL ====================
@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_rol")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, unique = true, length = 60)
    private String nombre;
    
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
}

