package com.project.lab_clinico.repository;

import com.project.lab_clinico.entity.LaboratoristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoristaRepository extends JpaRepository<LaboratoristaEntity, Long> {
}
