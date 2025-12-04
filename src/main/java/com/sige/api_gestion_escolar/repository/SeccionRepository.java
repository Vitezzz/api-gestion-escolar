package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
    List<Seccion> findByCuatrimestre(Cuatrimestre cuatrimestre);
    List<Seccion> findByMateria(Materia materia);
    List<Seccion> findByDocente(Docente docente);
    List<Seccion> findByEstado(String estado);
    
    @Query("SELECT s FROM Seccion s WHERE s.cuatrimestre.id = :cuatrimestreId AND s.materia.id = :materiaId")
    List<Seccion> findByCuatrimestreAndMateria(
        @Param("cuatrimestreId") Integer cuatrimestreId,
        @Param("materiaId") Integer materiaId
    );
    
    @Query("SELECT s FROM Seccion s WHERE s.docente.id = :docenteId AND s.cuatrimestre.activo = true")
    List<Seccion> findSeccionesActivasDeDocente(@Param("docenteId") Integer docenteId);
    
    @Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.seccion.id = :seccionId AND i.estado = 'ACTIVA'")
    Long contarInscritosEnSeccion(@Param("seccionId") Integer seccionId);
}