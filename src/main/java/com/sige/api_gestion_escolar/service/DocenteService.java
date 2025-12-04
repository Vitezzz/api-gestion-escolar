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
public class DocenteService {
    
    private final DocenteRepository docenteRepository;
    private final DepartamentoRepository departamentoRepository;
    
    public List<DocenteDTO> listarTodos() {
        return docenteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<DocenteDTO> listarActivos() {
        return docenteRepository.findByActivoTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public DocenteDTO obtenerPorId(Integer id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        return convertirADTO(docente);
    }
    
    public DocenteDTO crear(DocenteDTO dto) {
        if (docenteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe");
        }
        
        Docente docente = Docente.builder()
                .nombre(dto.getNombre())
                .apellidoPaterno(dto.getApellidoPaterno())
                .apellidoMaterno(dto.getApellidoMaterno())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
        
        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            docente.setDepartamento(departamento);
        }
        
        docente = docenteRepository.save(docente);
        return convertirADTO(docente);
    }
    
    public DocenteDTO actualizar(Integer id, DocenteDTO dto) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        
        docente.setNombre(dto.getNombre());
        docente.setApellidoPaterno(dto.getApellidoPaterno());
        docente.setApellidoMaterno(dto.getApellidoMaterno());
        docente.setEmail(dto.getEmail());
        docente.setTelefono(dto.getTelefono());
        docente.setActivo(dto.getActivo());
        
        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            docente.setDepartamento(departamento);
        }
        
        docente = docenteRepository.save(docente);
        return convertirADTO(docente);
    }
    
    public void eliminar(Integer id) {
        docenteRepository.deleteById(id);
    }
    
    private DocenteDTO convertirADTO(Docente docente) {
        return DocenteDTO.builder()
                .id(docente.getId())
                .nombre(docente.getNombre())
                .apellidoPaterno(docente.getApellidoPaterno())
                .apellidoMaterno(docente.getApellidoMaterno())
                .email(docente.getEmail())
                .telefono(docente.getTelefono())
                .departamentoId(docente.getDepartamento() != null ? docente.getDepartamento().getId() : null)
                .departamentoNombre(docente.getDepartamento() != null ? docente.getDepartamento().getNombre() : null)
                .activo(docente.getActivo())
                .build();
    }
}
