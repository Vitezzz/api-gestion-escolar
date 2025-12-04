package com.sige.api_gestion_escolar.repository;

import com.sige.api_gestion_escolar.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByEstudiante(Estudiante estudiante);
    List<Pago> findByConcepto(String concepto);
    
    @Query("SELECT p FROM Pago p WHERE p.estudiante.id = :estudianteId ORDER BY p.fecha DESC")
    List<Pago> findPagosPorEstudianteOrdenados(@Param("estudianteId") Integer estudianteId);
    
    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.estudiante.id = :estudianteId")
    BigDecimal calcularTotalPagosEstudiante(@Param("estudianteId") Integer estudianteId);
}
