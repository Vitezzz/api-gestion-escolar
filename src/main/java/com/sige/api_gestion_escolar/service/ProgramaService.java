package com.sige.api_gestion_escolar.service;


import com.sige.api_gestion_escolar.dto.*;
import com.sige.api_gestion_escolar.entity.*;
import com.sige.api_gestion_escolar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramaService {
    
    private final ProgramaRepository programaRepository;
    
    public List<ProgramaDTO> listarTodos() {
        return programaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ProgramaDTO> listarActivos() {
        return programaRepository.findByActivoTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public ProgramaDTO obtenerPorId(Integer id) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        return convertirADTO(programa);
    }
    
    public ProgramaDTO crear(ProgramaDTO dto) {
        Programa programa = Programa.builder()
                .nombre(dto.getNombre())
                .clave(dto.getClave())
                .duracionCuatrimestres(dto.getDuracionCuatrimestres())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
        
        programa = programaRepository.save(programa);
        return convertirADTO(programa);
    }
    
    public ProgramaDTO actualizar(Integer id, ProgramaDTO dto) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        
        programa.setNombre(dto.getNombre());
        programa.setClave(dto.getClave());
        programa.setDuracionCuatrimestres(dto.getDuracionCuatrimestres());
        programa.setDescripcion(dto.getDescripcion());
        programa.setActivo(dto.getActivo());
        
        programa = programaRepository.save(programa);
        return convertirADTO(programa);
    }
    
    public void eliminar(Integer id) {
        programaRepository.deleteById(id);
    }
    
    private ProgramaDTO convertirADTO(Programa programa) {
        return ProgramaDTO.builder()
                .id(programa.getId())
                .nombre(programa.getNombre())
                .clave(programa.getClave())
                .duracionCuatrimestres(programa.getDuracionCuatrimestres())
                .descripcion(programa.getDescripcion())
                .activo(programa.getActivo())
                .build();
    }
}
