package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer> {
    List<Asesor> findByDocente(Docente docente);
    List<Asesor> findByEstudiante(Estudiante estudiante);
    
    @Query("SELECT a FROM Asesor a WHERE a.docente.id = :docenteId")
    List<Asesor> findEstudiantesAsesoradosPorDocente(@Param("docenteId") Integer docenteId);
}

