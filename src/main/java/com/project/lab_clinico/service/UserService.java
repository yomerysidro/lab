package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.TypeUser;
import com.project.lab_clinico.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> listar();
    Optional<UserEntity> buscarPorId(Long id);
    UserEntity guardar(UserEntity user);
    UserEntity actualizar(Long id, UserEntity user);
    void eliminar(Long id);
    List<UserEntity> listarPorTipo(TypeUser tipoUsuario);
    Optional<UserEntity> buscarPorDniYTipo(String dni, TypeUser tipoUsuario);
}
