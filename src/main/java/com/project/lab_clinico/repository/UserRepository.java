package com.project.lab_clinico.repository;

import com.project.lab_clinico.entity.TypeUser;
import com.project.lab_clinico.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDniAndTipoUsuario(String dni, TypeUser tipoUsuario);
    List<UserEntity> findAllByTipoUsuario(TypeUser tipoUsuario);
}
