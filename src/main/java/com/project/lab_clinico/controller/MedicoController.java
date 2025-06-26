package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.MedicoEntity;
import com.project.lab_clinico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

  // Listar todos los médicos
    @GetMapping
    public List<MedicoEntity> listarTodos() {
        return medicoService.listarTodos();
    }

    // Buscar médico por id
    @GetMapping("/{id}")
    public ResponseEntity<MedicoEntity> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear médico
    @PostMapping("/create")
    public ResponseEntity<MedicoEntity> crearMedico(@RequestBody MedicoEntity medico) {
        MedicoEntity creado = medicoService.guardar(medico);
        return ResponseEntity.ok(creado);
    }

    // Actualizar médico
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicoEntity> actualizarMedico(@PathVariable Long id, @RequestBody MedicoEntity medico) {
        try {
            MedicoEntity actualizado = medicoService.actualizar(id, medico);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar médico
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
