package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.OrderEntity;
import com.project.lab_clinico.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<OrderEntity> crearOrden(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.guardar(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> listarOrdenes() {
        return ResponseEntity.ok(orderService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> obtenerPorId(@PathVariable Long id) {
        Optional<OrderEntity> orden = orderService.buscarPorId(id);
        return orden.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> actualizar(@PathVariable Long id, @RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.actualizar(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        orderService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
