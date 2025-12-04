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
@RequestMapping("/api/programas")
@RequiredArgsConstructor
@Tag(name = "Programas", description = "Gestión de programas académicos")
public class ProgramaController {
    
    private final ProgramaService programaService;
    
    @GetMapping
    @Operation(summary = "Listar todos los programas")
    public ResponseEntity<List<ProgramaDTO>> listarTodos() {
        return ResponseEntity.ok(programaService.listarTodos());
    }
    
    @GetMapping("/activos")
    @Operation(summary = "Listar programas activos")
    public ResponseEntity<List<ProgramaDTO>> listarActivos() {
        return ResponseEntity.ok(programaService.listarActivos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener programa por ID")
    public ResponseEntity<ProgramaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(programaService.obtenerPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nuevo programa")
    public ResponseEntity<ProgramaDTO> crear(@Valid @RequestBody ProgramaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programaService.crear(dto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar programa")
    public ResponseEntity<ProgramaDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProgramaDTO dto) {
        return ResponseEntity.ok(programaService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar programa")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        programaService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Programa eliminado exitosamente"));
    }
}

