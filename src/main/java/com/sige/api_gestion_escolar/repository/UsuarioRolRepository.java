package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {
    List<UsuarioRol> findByUsuario(Usuario usuario);
    List<UsuarioRol> findByRol(Rol rol);
    void deleteByUsuarioAndRol(Usuario usuario, Rol rol);
}
