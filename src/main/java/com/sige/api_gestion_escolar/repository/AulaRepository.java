package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
    List<Aula> findByEdificio(String edificio);
    List<Aula> findByCapacidadGreaterThanEqual(Integer capacidad);
    Optional<Aula> findByNombre(String nombre);
}