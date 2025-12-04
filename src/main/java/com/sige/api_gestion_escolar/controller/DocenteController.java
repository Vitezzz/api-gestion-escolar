package com.sige.api_gestion_escolar.controller;

import com.sige.api_gestion_escolar.dto.*;
import com.sige.api_gestion_escolar.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
@Tag(name = "Docentes", description = "Gestión de docentes")
public class DocenteController {
    
    private final DocenteService docenteService;
    
    @GetMapping
    @Operation(summary = "Listar todos los docentes")
    public ResponseEntity<List<DocenteDTO>> listarTodos() {
        return ResponseEntity.ok(docenteService.listarTodos());
    }
    
    @GetMapping("/activos")
    @Operation(summary = "Listar docentes activos")
    public ResponseEntity<List<DocenteDTO>> listarActivos() {
        return ResponseEntity.ok(docenteService.listarActivos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener docente por ID")
    public ResponseEntity<DocenteDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nuevo docente")
    public ResponseEntity<?> crear(@Valid @RequestBody DocenteDTO dto) {
        try {
            DocenteDTO nuevoDocente = docenteService.crear(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDocente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(MessageResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar docente")
    public ResponseEntity<DocenteDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DocenteDTO dto) {
        return ResponseEntity.ok(docenteService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar docente")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        docenteService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Docente eliminado exitosamente"));
    }
}
