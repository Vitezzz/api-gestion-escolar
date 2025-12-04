package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByInscripcion(Inscripcion inscripcion);
    List<Calificacion> findByEvaluacion(Evaluacion evaluacion);
    
    @Query("SELECT c FROM Calificacion c WHERE c.inscripcion.estudiante.id = :estudianteId " +
           "AND c.inscripcion.seccion.cuatrimestre.id = :cuatrimestreId")
    List<Calificacion> findCalificacionesEstudiantePorCuatrimestre(
        @Param("estudianteId") Integer estudianteId,
        @Param("cuatrimestreId") Integer cuatrimestreId
    );
    
    Optional<Calificacion> findByInscripcionAndEvaluacion(Inscripcion inscripcion, Evaluacion evaluacion);
}