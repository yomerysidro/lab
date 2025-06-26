package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.MedicoEntity;
import com.project.lab_clinico.repository.MedicoRepository;
import com.project.lab_clinico.service.MedicoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicoServiceImpl implements MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl (MedicoRepository medicoRepository){
        this.medicoRepository= medicoRepository;
    }

    @Override
    public List<MedicoEntity> listarTodos() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<MedicoEntity> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public MedicoEntity guardar(MedicoEntity medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public MedicoEntity actualizar(Long id, MedicoEntity medico) {
        return medicoRepository.findById(id)
                .map(m -> {
                    m.setEspecialidad(medico.getEspecialidad());
                    m.setUserEntity(medico.getUserEntity());
                    // actualiza otras propiedades si las tienes
                    return medicoRepository.save(m);
                }).orElseThrow(() -> new RuntimeException("Médico no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
