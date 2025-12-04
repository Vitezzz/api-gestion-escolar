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
@RequestMapping("/api/secciones")
@RequiredArgsConstructor
@Tag(name = "Secciones", description = "Gestión de secciones")
public class SeccionController {
    
    private final SeccionService seccionService;
    
    @GetMapping
    @Operation(summary = "Listar todas las secciones")
    public ResponseEntity<List<SeccionDTO>> listarTodas() {
        return ResponseEntity.ok(seccionService.listarTodas());
    }
    
    @GetMapping("/cuatrimestre/{cuatrimestreId}")
    @Operation(summary = "Listar secciones por cuatrimestre")
    public ResponseEntity<List<SeccionDTO>> listarPorCuatrimestre(@PathVariable Integer cuatrimestreId) {
        return ResponseEntity.ok(seccionService.listarPorCuatrimestre(cuatrimestreId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener sección por ID")
    public ResponseEntity<SeccionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(seccionService.obtenerPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nueva sección")
    public ResponseEntity<SeccionDTO> crear(@Valid @RequestBody SeccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seccionService.crear(dto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar sección")
    public ResponseEntity<SeccionDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody SeccionDTO dto) {
        return ResponseEntity.ok(seccionService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar sección")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        seccionService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Sección eliminada exitosamente"));
    }
}