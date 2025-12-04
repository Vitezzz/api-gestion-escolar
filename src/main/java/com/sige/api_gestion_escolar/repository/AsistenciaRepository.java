package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findBySeccion(Seccion seccion);
    List<Asistencia> findByEstudiante(Estudiante estudiante);
    List<Asistencia> findByFecha(LocalDate fecha);
    
    @Query("SELECT a FROM Asistencia a WHERE a.seccion.id = :seccionId AND a.fecha = :fecha")
    List<Asistencia> findBySeccionAndFecha(
        @Param("seccionId") Integer seccionId,
        @Param("fecha") LocalDate fecha
    );
    
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.estudiante.id = :estudianteId " +
           "AND a.seccion.id = :seccionId AND a.estado = 'PRESENTE'")
    Long contarAsistenciasPresentes(
        @Param("estudianteId") Integer estudianteId,
        @Param("seccionId") Integer seccionId
    );
}

