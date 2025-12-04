package com.sige.api_gestion_escolar.entity;
import jakarta.persistence.*;
import lombok.*;


// ==================== USUARIO_ROL ====================
@Entity
@Table(name = "usuario_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_usuario_rol")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cve_rol", nullable = false)
    private Rol rol;
}
