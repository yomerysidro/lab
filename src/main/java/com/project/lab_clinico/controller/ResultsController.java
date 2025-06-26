package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.ResultEntity;
import com.project.lab_clinico.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class ResultsController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/create")
    public ResponseEntity<ResultEntity> create(@RequestBody ResultEntity result) {
        return ResponseEntity.ok(resultService.save(result));
    }

    @GetMapping
    public ResponseEntity<List<ResultEntity>> getAll() {
        return ResponseEntity.ok(resultService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultEntity> getById(@PathVariable Long id) {
        Optional<ResultEntity> result = resultService.findById(id);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order-detail/{idOrdenDetalle}")
    public ResponseEntity<List<ResultEntity>> getByOrderDetail(@PathVariable Long idOrdenDetalle) {
        return ResponseEntity.ok(resultService.findByOrderDetailId(idOrdenDetalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultEntity> update(@PathVariable Long id, @RequestBody ResultEntity result) {
        return ResponseEntity.ok(resultService.update(id, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resultService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
