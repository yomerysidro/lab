package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.LaboratoristaEntity;
import com.project.lab_clinico.service.LaboratoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratoristas")
public class LaboratoristaController {
    @Autowired
    private LaboratoristaService laboratoristaService;

    @GetMapping
    public List<LaboratoristaEntity> listarTodos() {
        return laboratoristaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoristaEntity> buscarPorId(@PathVariable Long id) {
        return laboratoristaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<LaboratoristaEntity> crear(@RequestBody LaboratoristaEntity laboratorista) {
        LaboratoristaEntity creado = laboratoristaService.guardar(laboratorista);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LaboratoristaEntity> actualizar(@PathVariable Long id, @RequestBody LaboratoristaEntity laboratorista) {
        try {
            LaboratoristaEntity actualizado = laboratoristaService.actualizar(id, laboratorista);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        laboratoristaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
