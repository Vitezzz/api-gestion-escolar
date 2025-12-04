package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// ==================== MATERIA REPOSITORY ====================
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    Optional<Materia> findByCodigo(String codigo);
    List<Materia> findByActivoTrue();
    List<Materia> findByDepartamento(Departamento departamento);
    List<Materia> findByNombreContainingIgnoreCase(String nombre);
    List<Materia> findByCreditos(Integer creditos);
}