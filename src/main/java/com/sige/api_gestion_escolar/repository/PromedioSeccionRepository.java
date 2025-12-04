package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromedioSeccionRepository extends JpaRepository<PromedioSeccion, Integer> {
    List<PromedioSeccion> findByInscripcion(Inscripcion inscripcion);
    
    @Query("SELECT ps FROM PromedioSeccion ps WHERE ps.inscripcion.estudiante.id = :estudianteId")
    List<PromedioSeccion> findByEstudiante(@Param("estudianteId") Integer estudianteId);
    
    Optional<PromedioSeccion> findByInscripcionAndVistoFalse(Inscripcion inscripcion);
}

