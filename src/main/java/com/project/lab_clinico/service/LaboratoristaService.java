package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.LaboratoristaEntity;

import java.util.List;
import java.util.Optional;

public interface LaboratoristaService {
    List<LaboratoristaEntity> listar();
    Optional<LaboratoristaEntity> buscarPorId(Long id);
    LaboratoristaEntity guardar(LaboratoristaEntity laboratorista);
    LaboratoristaEntity actualizar(Long id, LaboratoristaEntity laboratorista);
    void eliminar(Long id);
}
