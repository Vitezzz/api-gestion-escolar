package com.sige.api_gestion_escolar.service;


import com.sige.api_gestion_escolar.dto.*;
import com.sige.api_gestion_escolar.entity.*;
import com.sige.api_gestion_escolar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// ==================== ESTUDIANTE SERVICE ====================
@Service
@RequiredArgsConstructor
@Transactional
public class EstudianteService {
    
    private final EstudianteRepository estudianteRepository;
    private final ProgramaRepository programaRepository;
    
    public List<EstudianteDTO> listarTodos() {
        return estudianteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public EstudianteDTO obtenerPorId(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return convertirADTO(estudiante);
    }
    
    public EstudianteDTO obtenerPorMatricula(String matricula) {
        Estudiante estudiante = estudianteRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return convertirADTO(estudiante);
    }
    
    public List<EstudianteDTO> buscar(String busqueda) {
        return estudianteRepository.buscarEstudiantes(busqueda).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public EstudianteDTO crear(EstudianteDTO dto) {
        if (estudianteRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw new RuntimeException("La matrícula ya existe");
        }
        
        if (estudianteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe");
        }
        
        Estudiante estudiante = Estudiante.builder()
                .matricula(dto.getMatricula())
                .nombre(dto.getNombre())
                .apellidoPaterno(dto.getApellidoPaterno())
                .apellidoMaterno(dto.getApellidoMaterno())
                .fechaNacimiento(dto.getFechaNacimiento())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .fechaIngreso(dto.getFechaIngreso())
                .cuatrimestreActual(dto.getCuatrimestreActual() != null ? dto.getCuatrimestreActual() : 1)
                .status(dto.getStatus() != null ? dto.getStatus() : "ACTIVO")
                .build();
        
        if (dto.getProgramaId() != null) {
            Programa programa = programaRepository.findById(dto.getProgramaId())
                    .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
            estudiante.setPrograma(programa);
        }
        
        estudiante = estudianteRepository.save(estudiante);
        return convertirADTO(estudiante);
    }
    
    public EstudianteDTO actualizar(Integer id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellidoPaterno(dto.getApellidoPaterno());
        estudiante.setApellidoMaterno(dto.getApellidoMaterno());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setEmail(dto.getEmail());
        estudiante.setTelefono(dto.getTelefono());
        estudiante.setCuatrimestreActual(dto.getCuatrimestreActual());
        estudiante.setStatus(dto.getStatus());
        
        if (dto.getProgramaId() != null) {
            Programa programa = programaRepository.findById(dto.getProgramaId())
                    .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
            estudiante.setPrograma(programa);
        }
        
        estudiante = estudianteRepository.save(estudiante);
        return convertirADTO(estudiante);
    }
    
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id);
    }
    
    private EstudianteDTO convertirADTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .matricula(estudiante.getMatricula())
                .nombre(estudiante.getNombre())
                .apellidoPaterno(estudiante.getApellidoPaterno())
                .apellidoMaterno(estudiante.getApellidoMaterno())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .email(estudiante.getEmail())
                .telefono(estudiante.getTelefono())
                .programaId(estudiante.getPrograma() != null ? estudiante.getPrograma().getId() : null)
                .programaNombre(estudiante.getPrograma() != null ? estudiante.getPrograma().getNombre() : null)
                .fechaIngreso(estudiante.getFechaIngreso())
                .cuatrimestreActual(estudiante.getCuatrimestreActual())
                .status(estudiante.getStatus())
                .build();
    }
}

