package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.OrderDetailEntity;
import com.project.lab_clinico.repository.OrderDetailRepository;
import com.project.lab_clinico.repository.OrderRepository;
import com.project.lab_clinico.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDetailEntity guardar(OrderDetailEntity detalle) {
        detalle.setFechaCreacion(LocalDateTime.now());
        detalle.setFechaActualizacion(LocalDateTime.now());

        // Asegura que orderEntity venga con toda su información completa
        Long idOrden = detalle.getOrderEntity().getIdOrden();
        detalle.setOrderEntity(
                orderRepository.findById(idOrden)
                        .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden))
        );

        return repository.save(detalle);
    }

    @Override
    public List<OrderDetailEntity> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<OrderDetailEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public OrderDetailEntity actualizar(Long id, OrderDetailEntity detalle) {
        OrderDetailEntity existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de orden no encontrado con ID: " + id));

        existente.setFechaActualizacion(LocalDateTime.now());
        existente.setOrderEntity(detalle.getOrderEntity());
        // Si agregas parámetro u otros campos, también actualízalos aquí

        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el detalle con ID: " + id);
        }
        repository.deleteById(id);
    }
}
