package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders_details")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenDetalle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden")
    @JsonIgnoreProperties({"detalles"}) // evita recursividad, pero permite ver paciente, laboratorista, etc.
    private OrderEntity orderEntity;

    @Column(name = "fecha_creaccion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "ordenDetalle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ResultEntity> resultados;

    public Long getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public void setIdOrdenDetalle(Long idOrdenDetalle) {
        this.idOrdenDetalle = idOrdenDetalle;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<ResultEntity> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultEntity> resultados) {
        this.resultados = resultados;
    }
}
