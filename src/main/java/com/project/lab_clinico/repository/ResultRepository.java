package com.project.lab_clinico.repository;

import com.project.lab_clinico.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findByOrdenDetalle_IdOrdenDetalle(Long idOrdenDetalle);
}
