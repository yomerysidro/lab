package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.PacientEntity;

import java.util.List;
import java.util.Optional;

public interface PacientService {
    List<PacientEntity> listarTodos();
    Optional<PacientEntity> buscarPorId(Long id);
    PacientEntity guardar(PacientEntity paciente);
    PacientEntity actualizar(Long id, PacientEntity paciente);
    void eliminar(Long id);


}
