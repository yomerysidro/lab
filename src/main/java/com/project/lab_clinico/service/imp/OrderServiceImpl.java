package com.project.lab_clinico.service.imp;

import com.project.lab_clinico.entity.OrderEntity;
import com.project.lab_clinico.repository.LaboratoristaRepository;
import com.project.lab_clinico.repository.MedicoRepository;
import com.project.lab_clinico.repository.OrderRepository;
import com.project.lab_clinico.repository.PacientRepository;
import com.project.lab_clinico.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private LaboratoristaRepository laboratoristaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        order.setFechaCreacion(LocalDateTime.now());
        order.setFechaActualizacion(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> listarTodos() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> buscarPorId(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderEntity guardar(OrderEntity order) {
        order.setFechaCreacion(LocalDateTime.now());
        order.setFechaActualizacion(LocalDateTime.now());

        // Obtener paciente completo
        if (order.getPaciente() != null && order.getPaciente().getId_paciente() != null) {
            order.setPaciente(pacientRepository.findById(order.getPaciente().getId_paciente())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado")));
        }

        // Obtener laboratorista completo
        if (order.getLaboratorista() != null && order.getLaboratorista().getId_laboratorista() != null) {
            order.setLaboratorista(laboratoristaRepository.findById(order.getLaboratorista().getId_laboratorista())
                    .orElseThrow(() -> new EntityNotFoundException("Laboratorista no encontrado")));
        }

        // Obtener médico completo
        if (order.getMedico() != null && order.getMedico().getId_medico() != null) {
            order.setMedico(medicoRepository.findById(order.getMedico().getId_medico())
                    .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado")));
        }

        return orderRepository.save(order);
    }

    @Override
    public OrderEntity actualizar(Long id, OrderEntity order) {
        OrderEntity existente = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + id));

        existente.setEstado(order.getEstado());
        existente.setCodigoOrden(order.getCodigoOrden());
        existente.setFechaActualizacion(LocalDateTime.now());
        existente.setPaciente(order.getPaciente());
        existente.setLaboratorista(order.getLaboratorista());
        existente.setMedico(order.getMedico());

        return orderRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró la orden con ID: " + id);
        }
        orderRepository.deleteById(id);
    }

}
