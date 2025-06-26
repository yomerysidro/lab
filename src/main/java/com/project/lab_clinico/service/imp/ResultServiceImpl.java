package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.LaboratoristaEntity;
import com.project.lab_clinico.entity.OrderDetailEntity;
import com.project.lab_clinico.entity.ResultEntity;
import com.project.lab_clinico.repository.LaboratoristaRepository;
import com.project.lab_clinico.repository.OrderDetailRepository;
import com.project.lab_clinico.repository.ResultRepository;
import com.project.lab_clinico.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private LaboratoristaRepository laboratoristaRepository;
    @Override
    public ResultEntity save(ResultEntity result) {
        result.setFechaRegistro(LocalDateTime.now());
        result.setFechaActualizacion(LocalDateTime.now());

        // Asegurar que las entidades referenciadas existen
        OrderDetailEntity ordenDetalle = orderDetailRepository.findById(
                result.getOrdenDetalle().getIdOrdenDetalle()
        ).orElseThrow(() -> new EntityNotFoundException("Detalle de orden no encontrado"));

        LaboratoristaEntity laboratorista = laboratoristaRepository.findById(
                result.getLaboratorista().getId_laboratorista()
        ).orElseThrow(() -> new EntityNotFoundException("Laboratorista no encontrado"));

        result.setOrdenDetalle(ordenDetalle);
        result.setLaboratorista(laboratorista);

        return resultRepository.save(result);
    }

    @Override
    public List<ResultEntity> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Optional<ResultEntity> findById(Long id) {
        return resultRepository.findById(id);
    }

    @Override
    public List<ResultEntity> findByOrderDetailId(Long idOrdenDetalle) {
        return resultRepository.findByOrdenDetalle_IdOrdenDetalle(idOrdenDetalle);
    }

    @Override
    public ResultEntity update(Long id, ResultEntity updated) {
        ResultEntity existing = resultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resultado no encontrado con ID: " + id));

        existing.setValorResultado(updated.getValorResultado());
        existing.setFechaActualizacion(LocalDateTime.now());
        existing.setLaboratorista(updated.getLaboratorista());
        existing.setOrdenDetalle(updated.getOrdenDetalle());

        return resultRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!resultRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el resultado con ID: " + id);
        }
        resultRepository.deleteById(id);
    }
}
