package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByMatricula(String matricula);
    Optional<Estudiante> findByEmail(String email);
    List<Estudiante> findByPrograma(Programa programa);
    List<Estudiante> findByStatus(String status);
    List<Estudiante> findByCuatrimestreActual(Integer cuatrimestre);
    
    @Query("SELECT e FROM Estudiante e WHERE " +
           "LOWER(e.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.apellidoPaterno) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.matricula) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Estudiante> buscarEstudiantes(@Param("busqueda") String busqueda);
    
    @Query("SELECT COUNT(e) FROM Estudiante e WHERE e.programa.id = :programaId AND e.status = 'ACTIVO'")
    Long contarEstudiantesActivosPorPrograma(@Param("programaId") Integer programaId);
}
