package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.OrderDetailEntity;
import com.project.lab_clinico.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @PostMapping("/create")
    public ResponseEntity<OrderDetailEntity> crear(@RequestBody OrderDetailEntity detalle) {
        return ResponseEntity.ok(service.guardar(detalle));
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailEntity>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> obtenerPorId(@PathVariable Long id) {
        Optional<OrderDetailEntity> encontrado = service.buscarPorId(id);
        return encontrado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> actualizar(@PathVariable Long id, @RequestBody OrderDetailEntity detalle) {
        return ResponseEntity.ok(service.actualizar(id, detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
