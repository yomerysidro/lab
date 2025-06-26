package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.PacientEntity;
import com.project.lab_clinico.repository.PacientRepository;
import com.project.lab_clinico.service.PacientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientServiceImpl implements PacientService {
    private final PacientRepository pacientRepository;

    public PacientServiceImpl(PacientRepository pacienteRepository) {
        this.pacientRepository = pacienteRepository;
    }

    @Override
    public List<PacientEntity> listarTodos() {
        return pacientRepository.findAll();
    }

    @Override
    public Optional<PacientEntity> buscarPorId(Long id) {
        return pacientRepository.findById(id);
    }

    @Override
    public PacientEntity guardar(PacientEntity paciente) {
        return pacientRepository.save(paciente);
    }

    @Override
    public PacientEntity actualizar(Long id, PacientEntity paciente) {
        return pacientRepository.findById(id)
                .map(existing -> {
                    existing.setNumero_historia(paciente.getNumero_historia());
                    existing.setUserEntity(paciente.getUserEntity());
                    // Actualiza otras propiedades si las tienes
                    return pacientRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        pacientRepository.deleteById(id);
    }
}
