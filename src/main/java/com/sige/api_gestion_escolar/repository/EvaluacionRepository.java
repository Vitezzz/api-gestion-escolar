package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    List<Evaluacion> findBySeccion(Seccion seccion);
    List<Evaluacion> findByTipo(String tipo);
    
    @Query("SELECT e FROM Evaluacion e WHERE e.seccion.id = :seccionId ORDER BY e.fecha")
    List<Evaluacion> findBySeccionOrderByFecha(@Param("seccionId") Integer seccionId);
}
