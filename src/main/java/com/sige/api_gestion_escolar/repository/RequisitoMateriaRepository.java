package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RequisitoMateriaRepository extends JpaRepository<RequisitoMateria, Integer> {
    List<RequisitoMateria> findByMateria(Materia materia);
    List<RequisitoMateria> findByMateriaPrerrequisito(Materia materia);
    
    @Query("SELECT rm FROM RequisitoMateria rm WHERE rm.materia.id = :materiaId")
    List<RequisitoMateria> findPrerequisitosDeMateria(@Param("materiaId") Integer materiaId);
}
