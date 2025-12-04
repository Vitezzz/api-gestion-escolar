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
public class SeccionService {
    
    private final SeccionRepository seccionRepository;
    private final MateriaRepository materiaRepository;
    private final CuatrimestreRepository cuatrimestreRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    
    public List<SeccionDTO> listarTodas() {
        return seccionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<SeccionDTO> listarPorCuatrimestre(Integer cuatrimestreId) {
        Cuatrimestre cuatrimestre = cuatrimestreRepository.findById(cuatrimestreId)
                .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
        return seccionRepository.findByCuatrimestre(cuatrimestre).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public SeccionDTO obtenerPorId(Integer id) {
        Seccion seccion = seccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección no encontrada"));
        return convertirADTO(seccion);
    }
    
    public SeccionDTO crear(SeccionDTO dto) {
        Materia materia = materiaRepository.findById(dto.getMateriaId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        
        Cuatrimestre cuatrimestre = cuatrimestreRepository.findById(dto.getCuatrimestreId())
                .orElseThrow(() -> new RuntimeException("Cuatrimestre no encontrado"));
        
        Seccion seccion = Seccion.builder()
                .materia(materia)
                .cuatrimestre(cuatrimestre)
                .grupo(dto.getGrupo())
                .maxAlumnos(dto.getMaxAlumnos())
                .diaHorario(dto.getDiaHorario())
                .tipo(dto.getTipo() != null ? dto.getTipo() : "REGULAR")
                .estado(dto.getEstado() != null ? dto.getEstado() : "ABIERTA")
                .build();
        
        if (dto.getDocenteId() != null) {
            Docente docente = docenteRepository.findById(dto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
            seccion.setDocente(docente);
        }
        
        if (dto.getAulaId() != null) {
            Aula aula = aulaRepository.findById(dto.getAulaId())
                    .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
            seccion.setAula(aula);
        }
        
        seccion = seccionRepository.save(seccion);
        return convertirADTO(seccion);
    }
    
    public SeccionDTO actualizar(Integer id, SeccionDTO dto) {
        Seccion seccion = seccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección no encontrada"));
        
        seccion.setGrupo(dto.getGrupo());
        seccion.setMaxAlumnos(dto.getMaxAlumnos());
        seccion.setDiaHorario(dto.getDiaHorario());
        seccion.setTipo(dto.getTipo());
        seccion.setEstado(dto.getEstado());
        
        if (dto.getDocenteId() != null) {
            Docente docente = docenteRepository.findById(dto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
            seccion.setDocente(docente);
        }
        
        if (dto.getAulaId() != null) {
            Aula aula = aulaRepository.findById(dto.getAulaId())
                    .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
            seccion.setAula(aula);
        }
        
        seccion = seccionRepository.save(seccion);
        return convertirADTO(seccion);
    }
    
    public void eliminar(Integer id) {
        seccionRepository.deleteById(id);
    }
    
    private SeccionDTO convertirADTO(Seccion seccion) {
        Long inscritos = seccionRepository.contarInscritosEnSeccion(seccion.getId());
        
        return SeccionDTO.builder()
                .id(seccion.getId())
                .materiaId(seccion.getMateria().getId())
                .materiaNombre(seccion.getMateria().getNombre())
                .materiaCodigo(seccion.getMateria().getCodigo())
                .cuatrimestreId(seccion.getCuatrimestre().getId())
                .cuatrimestrePeriodo(seccion.getCuatrimestre().getPeriodo())
                .grupo(seccion.getGrupo())
                .docenteId(seccion.getDocente() != null ? seccion.getDocente().getId() : null)
                .docenteNombre(seccion.getDocente() != null ? 
                        seccion.getDocente().getNombre() + " " + seccion.getDocente().getApellidoPaterno() : null)
                .aulaId(seccion.getAula() != null ? seccion.getAula().getId() : null)
                .aulaNombre(seccion.getAula() != null ? seccion.getAula().getNombre() : null)
                .maxAlumnos(seccion.getMaxAlumnos())
                .inscritos(inscritos.intValue())
                .diaHorario(seccion.getDiaHorario())
                .tipo(seccion.getTipo())
                .estado(seccion.getEstado())
                .build();
    }
}