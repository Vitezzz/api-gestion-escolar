package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByEstudiante(Estudiante estudiante);
    List<Inscripcion> findBySeccion(Seccion seccion);
    List<Inscripcion> findByEstado(String estado);
    
    @Query("SELECT i FROM Inscripcion i WHERE i.estudiante.id = :estudianteId " +
           "AND i.seccion.cuatrimestre.id = :cuatrimestreId")
    List<Inscripcion> findByEstudianteAndCuatrimestre(
        @Param("estudianteId") Integer estudianteId,
        @Param("cuatrimestreId") Integer cuatrimestreId
    );
    
    @Query("SELECT i FROM Inscripcion i WHERE i.estudiante.id = :estudianteId " +
           "AND i.seccion.materia.id = :materiaId")
    Optional<Inscripcion> findByEstudianteAndMateria(
        @Param("estudianteId") Integer estudianteId,
        @Param("materiaId") Integer materiaId
    );
    
    boolean existsByEstudianteAndSeccion(Estudiante estudiante, Seccion seccion);
}