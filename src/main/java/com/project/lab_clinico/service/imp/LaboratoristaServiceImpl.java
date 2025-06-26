package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.LaboratoristaEntity;
import com.project.lab_clinico.repository.LaboratoristaRepository;
import com.project.lab_clinico.service.LaboratoristaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoristaServiceImpl implements LaboratoristaService {
    private final LaboratoristaRepository laboratoristaRepository;

    public LaboratoristaServiceImpl(LaboratoristaRepository laboratoristaRepository){
        this.laboratoristaRepository = laboratoristaRepository;
    }
    @Override
    public List<LaboratoristaEntity> listar() {
        return laboratoristaRepository.findAll();
    }

    @Override
    public Optional<LaboratoristaEntity> buscarPorId(Long id) {
        return laboratoristaRepository.findById(id);

    }

    @Override
    public LaboratoristaEntity guardar(LaboratoristaEntity laboratorista) {
        return laboratoristaRepository.save(laboratorista);
    }

    @Override
    public LaboratoristaEntity actualizar(Long id, LaboratoristaEntity laboratorista) {
        return laboratoristaRepository.findById(id)
                .map(existing -> {
                    existing.setTurno(laboratorista.getTurno());
                    existing.setEmail(laboratorista.getEmail());
                    existing.setContraseña(laboratorista.getContraseña());
                    // Si quieres actualizar usuario relacionado, hazlo aquí con cuidado
                    return laboratoristaRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Laboratorista no encontrado con id " + id));

    }

    @Override
    public void eliminar(Long id) {
        laboratoristaRepository.deleteById(id);
    }


}
