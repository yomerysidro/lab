package com.project.lab_clinico.controller;

import com.project.lab_clinico.entity.*;
import com.project.lab_clinico.service.LaboratoristaService;
import com.project.lab_clinico.service.MedicoService;
import com.project.lab_clinico.service.PacientService;
import com.project.lab_clinico.service.UserService;
import com.project.lab_clinico.service.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  MedicoService medicoService;
    @Autowired
    private  LaboratoristaService laboratoristaService;
    @Autowired
    private  PacientService pacientService;



    // Listar todos
    @GetMapping
    public List<UserEntity> listarTodos() {
        return userService.listar();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear usuario
    @PostMapping("/create")
    public ResponseEntity<?> crearUsuario(@RequestBody UserEntity user) {

        // Guardar usuario primero
        UserEntity usuarioGuardado = userService.guardar(user);

        // Si es MEDICO y trae datos del medico, guardar registro en medicos
        if (user.getTipoUsuario() == TypeUser.MEDICO && user.getMedico() != null) {
            MedicoEntity medico = user.getMedico();
            medico.setUserEntity(usuarioGuardado);
            MedicoEntity medicoGuardado = medicoService.guardar(medico);
            // Limpiar el userEntity para evitar ciclo en la respuesta JSON
            medicoGuardado.setUserEntity(null);
            usuarioGuardado.setMedico(medicoGuardado);
        }

        // Si es LABORATORISTA y trae datos del laboratorista, guardar en la tabla laboratoristas
        if (user.getTipoUsuario() == TypeUser.LABORATORISTA && user.getLaboratorista() != null) {
            LaboratoristaEntity laboratorista = user.getLaboratorista();
            laboratorista.setUserEntity(usuarioGuardado);
            LaboratoristaEntity laboratoristaGuardado = laboratoristaService.guardar(laboratorista);
            // Limpiar el userEntity para evitar ciclo en la respuesta
            laboratoristaGuardado.setUserEntity(null);
            usuarioGuardado.setLaboratorista(laboratoristaGuardado);
        }


        // Si es PACIENTE y trae datos del paciente, guardar en la tabla pacientes
       if (user.getTipoUsuario() == TypeUser.PACIENTE && user.getPaciente() != null) {
           PacientEntity paciente = user.getPaciente();
           paciente.setUserEntity(usuarioGuardado);
           pacientService.guardar(paciente);
        }

        return ResponseEntity.ok(usuarioGuardado);
    }

    // Actualizar usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> actualizarUsuario(@PathVariable Long id, @RequestBody UserEntity user) {
        try {
            UserEntity actualizado = userService.actualizar(id, user);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        userService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/tipos")
    public TypeUser[] listarTiposUsuario() {
        return TypeUser.values();
    }
    // Listar usuarios por tipo
    @GetMapping("/tipo/{tipo}")
    public List<UserEntity> listarPorTipo(@PathVariable String tipo) {
        TypeUser tipoUsuario = TypeUser.valueOf(tipo.toUpperCase());
        return userService.listarPorTipo(tipoUsuario);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<UserEntity> buscarPacientePorDni(@PathVariable String dni) {
        TypeUser tipoPaciente = TypeUser.PACIENTE; // Fijamos solo PACIENTE
        return userService.buscarPorDniYTipo(dni, tipoPaciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
