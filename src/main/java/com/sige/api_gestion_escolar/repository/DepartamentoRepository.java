package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// ==================== DEPARTAMENTO REPOSITORY ====================
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByCodigo(String codigo);
    List<Departamento> findByUnidadAcademica(UnidadAcademica unidad);
    List<Departamento> findByNombreContainingIgnoreCase(String nombre);
}
