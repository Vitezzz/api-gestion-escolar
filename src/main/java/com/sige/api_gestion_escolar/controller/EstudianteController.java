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
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@Tag(name = "Estudiantes", description = "Gestión de estudiantes")
public class EstudianteController {
    
    private final EstudianteService estudianteService;
    
    @GetMapping
    @Operation(summary = "Listar todos los estudiantes")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR') or hasRole('DOCENTE')")
    public ResponseEntity<List<EstudianteDTO>> listarTodos() {
        return ResponseEntity.ok(estudianteService.listarTodos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudiante por ID")
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }
    
    @GetMapping("/matricula/{matricula}")
    @Operation(summary = "Obtener estudiante por matrícula")
    public ResponseEntity<EstudianteDTO> obtenerPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(estudianteService.obtenerPorMatricula(matricula));
    }
    
    @GetMapping("/buscar")
    @Operation(summary = "Buscar estudiantes")
    public ResponseEntity<List<EstudianteDTO>> buscar(@RequestParam String q) {
        return ResponseEntity.ok(estudianteService.buscar(q));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nuevo estudiante")
    public ResponseEntity<?> crear(@Valid @RequestBody EstudianteDTO dto) {
        try {
            EstudianteDTO nuevoEstudiante = estudianteService.crear(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(MessageResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar estudiante")
    public ResponseEntity<EstudianteDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EstudianteDTO dto) {
        return ResponseEntity.ok(estudianteService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar estudiante")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        estudianteService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Estudiante eliminado exitosamente"));
    }
}

