package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    Optional<Programa> findByClave(String clave);
    List<Programa> findByActivoTrue();
    List<Programa> findByNombreContainingIgnoreCase(String nombre);
}