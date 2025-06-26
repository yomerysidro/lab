package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.OrderDetailEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    OrderDetailEntity guardar(OrderDetailEntity detalle);
    List<OrderDetailEntity> listarTodos();
    Optional<OrderDetailEntity> buscarPorId(Long id);
    OrderDetailEntity actualizar(Long id, OrderDetailEntity detalle);
    void eliminar(Long id);
}
