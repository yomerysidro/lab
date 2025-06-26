package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.MedicoEntity;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    List<MedicoEntity> listarTodos();
    Optional<MedicoEntity> buscarPorId(Long id);
    MedicoEntity guardar(MedicoEntity medico);
    MedicoEntity actualizar(Long id, MedicoEntity medico);
    void eliminar(Long id);
}
