package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialEstadoEstudianteRepository extends JpaRepository<HistorialEstadoEstudiante, Integer> {
    List<HistorialEstadoEstudiante> findByEstudiante(Estudiante estudiante);
    
    @Query("SELECT h FROM HistorialEstadoEstudiante h WHERE h.estudiante.id = :estudianteId ORDER BY h.fecha DESC")
    List<HistorialEstadoEstudiante> findHistorialOrdenado(@Param("estudianteId") Integer estudianteId);
}