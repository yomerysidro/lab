package com.project.lab_clinico.service;

import com.project.lab_clinico.entity.ResultEntity;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    ResultEntity save(ResultEntity result);
    List<ResultEntity> findAll();
    Optional<ResultEntity> findById(Long id);
    List<ResultEntity> findByOrderDetailId(Long idOrdenDetalle);
    ResultEntity update(Long id, ResultEntity updated);
    void delete(Long id);
}
