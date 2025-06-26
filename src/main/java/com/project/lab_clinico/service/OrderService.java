package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderEntity saveOrder(OrderEntity order);
    List<OrderEntity> getAllOrders();
    List<OrderEntity> listarTodos();
    Optional<OrderEntity> buscarPorId(Long id);
    OrderEntity guardar(OrderEntity order);
    OrderEntity actualizar(Long id, OrderEntity order);
    void eliminar(Long id);
}
