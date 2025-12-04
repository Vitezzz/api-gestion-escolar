package com.sige.api_gestion_escolar.service;


import com.sige.api_gestion_escolar.dto.*;
import com.sige.api_gestion_escolar.entity.*;
import com.sige.api_gestion_escolar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// ==================== CUATRIMESTRE SERVICE ====================
@Service
@RequiredArgsConstructor
@Transactional
public class CuatrimestreService {
    
    private final CuatrimestreRepository cuatrimestreRepository;
    
    public List<CuatrimestreDTO> listarTodos() {
        return cuatrimestreRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<CuatrimestreDTO> listarActivos() {
        return cuatrimestreRepository.findByActivoTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public CuatrimestreDTO obtenerPorId(Integer id) {
        Cuatrimestre cuatrimestre = cuatrimestreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
        return convertirADTO(cuatrimestre);
    }
    
    public CuatrimestreDTO crear(CuatrimestreDTO dto) {
        Cuatrimestre cuatrimestre = Cuatrimestre.builder()
                .periodo(dto.getPeriodo())
                .anio(dto.getAnio())
                .numeroCuatrimestre(dto.getNumeroCuatrimestre())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
        
        cuatrimestre = cuatrimestreRepository.save(cuatrimestre);
        return convertirADTO(cuatrimestre);
    }
    
    public CuatrimestreDTO actualizar(Integer id, CuatrimestreDTO dto) {
        Cuatrimestre cuatrimestre = cuatrimestreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
        
        cuatrimestre.setPeriodo(dto.getPeriodo());
        cuatrimestre.setAnio(dto.getAnio());
        cuatrimestre.setNumeroCuatrimestre(dto.getNumeroCuatrimestre());
        cuatrimestre.setFechaInicio(dto.getFechaInicio());
        cuatrimestre.setFechaFin(dto.getFechaFin());
        cuatrimestre.setActivo(dto.getActivo());
        
        cuatrimestre = cuatrimestreRepository.save(cuatrimestre);
        return convertirADTO(cuatrimestre);
    }
    
    public void eliminar(Integer id) {
        cuatrimestreRepository.deleteById(id);
    }
    
    private CuatrimestreDTO convertirADTO(Cuatrimestre cuatrimestre) {
        return CuatrimestreDTO.builder()
                .id(cuatrimestre.getId())
                .periodo(cuatrimestre.getPeriodo())
                .anio(cuatrimestre.getAnio())
                .numeroCuatrimestre(cuatrimestre.getNumeroCuatrimestre())
                .fechaInicio(cuatrimestre.getFechaInicio())
                .fechaFin(cuatrimestre.getFechaFin())
                .activo(cuatrimestre.getActivo())
                .build();
    }
}