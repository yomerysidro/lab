package com.project.lab_clinico.repository;

import com.project.lab_clinico.entity.PacientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<PacientEntity, Long> {
}
