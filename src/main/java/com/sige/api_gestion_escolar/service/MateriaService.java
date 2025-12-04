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
public class MateriaService {
    
    private final MateriaRepository materiaRepository;
    private final DepartamentoRepository departamentoRepository;
    
    public List<MateriaDTO> listarTodas() {
        return materiaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<MateriaDTO> listarActivas() {
        return materiaRepository.findByActivoTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public MateriaDTO obtenerPorId(Integer id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        return convertirADTO(materia);
    }
    
    public MateriaDTO crear(MateriaDTO dto) {
        Materia materia = Materia.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .creditos(dto.getCreditos())
                .horasTeoria(dto.getHorasTeoria())
                .horasPractica(dto.getHorasPractica())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
        
        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            materia.setDepartamento(departamento);
        }
        
        materia = materiaRepository.save(materia);
        return convertirADTO(materia);
    }
    
    public MateriaDTO actualizar(Integer id, MateriaDTO dto) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        
        materia.setCodigo(dto.getCodigo());
        materia.setNombre(dto.getNombre());
        materia.setCreditos(dto.getCreditos());
        materia.setHorasTeoria(dto.getHorasTeoria());
        materia.setHorasPractica(dto.getHorasPractica());
        materia.setDescripcion(dto.getDescripcion());
        materia.setActivo(dto.getActivo());
        
        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            materia.setDepartamento(departamento);
        }
        
        materia = materiaRepository.save(materia);
        return convertirADTO(materia);
    }
    
    public void eliminar(Integer id) {
        materiaRepository.deleteById(id);
    }
    
    private MateriaDTO convertirADTO(Materia materia) {
        return MateriaDTO.builder()
                .id(materia.getId())
                .codigo(materia.getCodigo())
                .nombre(materia.getNombre())
                .creditos(materia.getCreditos())
                .horasTeoria(materia.getHorasTeoria())
                .horasPractica(materia.getHorasPractica())
                .departamentoId(materia.getDepartamento() != null ? materia.getDepartamento().getId() : null)
                .departamentoNombre(materia.getDepartamento() != null ? materia.getDepartamento().getNombre() : null)
                .descripcion(materia.getDescripcion())
                .activo(materia.getActivo())
                .build();
    }
}
