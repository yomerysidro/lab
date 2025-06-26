package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.PacientEntity;
import com.project.lab_clinico.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacientController {
    @Autowired
    private PacientService pacientService;



    // Listar todos los pacientes
    @GetMapping
    public List<PacientEntity> listarTodos() {
        return pacientService.listarTodos();
    }

    // Buscar paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<PacientEntity> buscarPorId(@PathVariable Long id) {
        return pacientService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear paciente
    @PostMapping("/create")
    public ResponseEntity<PacientEntity> crearPaciente(@RequestBody PacientEntity paciente) {
        PacientEntity creado = pacientService.guardar(paciente);
        return ResponseEntity.ok(creado);
    }

    // Actualizar paciente
    @PutMapping("/update/{id}")
    public ResponseEntity<PacientEntity> actualizarPaciente(@PathVariable Long id, @RequestBody PacientEntity paciente) {
        try {
            PacientEntity actualizado = pacientService.actualizar(id, paciente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar paciente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacientService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
