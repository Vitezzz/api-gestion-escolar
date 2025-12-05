package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByEmail(String email);
    List<Docente> findByActivoTrue();
    List<Docente> findByDepartamento(Departamento departamento);
    
    @Query("SELECT d FROM Docente d WHERE " +
           "LOWER(d.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(d.apellidoPaterno) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Docente> buscarPorNombre(@Param("busqueda") String busqueda);
}
