
package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// ==================== CUATRIMESTRE REPOSITORY ====================
@Repository
public interface CuatrimestreRepository extends JpaRepository<Cuatrimestre, Integer> {
    Optional<Cuatrimestre> findByPeriodo(String periodo);
    List<Cuatrimestre> findByActivoTrue();
    List<Cuatrimestre> findByAnio(Integer anio);
    Optional<Cuatrimestre> findByAnioAndNumeroCuatrimestre(Integer anio, Integer numero);
    
    @Query("SELECT c FROM Cuatrimestre c WHERE :fecha BETWEEN c.fechaInicio AND c.fechaFin")
    Optional<Cuatrimestre> findByFecha(@Param("fecha") LocalDate fecha);
}