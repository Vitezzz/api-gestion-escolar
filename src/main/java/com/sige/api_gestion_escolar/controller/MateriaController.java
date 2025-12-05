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
@RequestMapping("/api/materias")
@RequiredArgsConstructor
@Tag(name = "Materias", description = "Gestión de materias")
public class MateriaController {
    
    private final MateriaService materiaService;
    
    @GetMapping
    @Operation(summary = "Listar todas las materias")
    public ResponseEntity<List<MateriaDTO>> listarTodas() {
        return ResponseEntity.ok(materiaService.listarTodas());
    }
    
    @GetMapping("/activas")
    @Operation(summary = "Listar materias activas")
    public ResponseEntity<List<MateriaDTO>> listarActivas() {
        return ResponseEntity.ok(materiaService.listarActivas());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener materia por ID")
    public ResponseEntity<MateriaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(materiaService.obtenerPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nueva materia")
    public ResponseEntity<MateriaDTO> crear(@Valid @RequestBody MateriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materiaService.crear(dto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar materia")
    public ResponseEntity<MateriaDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody MateriaDTO dto) {
        return ResponseEntity.ok(materiaService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar materia")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        materiaService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Materia eliminada exitosamente"));
    }
}