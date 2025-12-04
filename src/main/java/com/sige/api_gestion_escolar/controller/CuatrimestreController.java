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
@RequestMapping("/api/cuatrimestres")
@RequiredArgsConstructor
@Tag(name = "Cuatrimestres", description = "Gestión de cuatrimestres")
public class CuatrimestreController {
    
    private final CuatrimestreService cuatrimestreService;
    
    @GetMapping
    @Operation(summary = "Listar todos los cuatrimestres")
    public ResponseEntity<List<CuatrimestreDTO>> listarTodos() {
        return ResponseEntity.ok(cuatrimestreService.listarTodos());
    }
    
    @GetMapping("/activos")
    @Operation(summary = "Listar cuatrimestres activos")
    public ResponseEntity<List<CuatrimestreDTO>> listarActivos() {
        return ResponseEntity.ok(cuatrimestreService.listarActivos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cuatrimestre por ID")
    public ResponseEntity<CuatrimestreDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(cuatrimestreService.obtenerPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Crear nuevo cuatrimestre")
    public ResponseEntity<CuatrimestreDTO> crear(@Valid @RequestBody CuatrimestreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cuatrimestreService.crear(dto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @Operation(summary = "Actualizar cuatrimestre")
    public ResponseEntity<CuatrimestreDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CuatrimestreDTO dto) {
        return ResponseEntity.ok(cuatrimestreService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar cuatrimestre")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        cuatrimestreService.eliminar(id);
        return ResponseEntity.ok(MessageResponse.success("Cuatrimestre eliminado exitosamente"));
    }
}
