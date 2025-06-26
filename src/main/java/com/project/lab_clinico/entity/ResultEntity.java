package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden_detalle")
    @JsonIgnoreProperties({"resultados"})
    private OrderDetailEntity ordenDetalle;

    @Column(name = "valor_resultado")
    private String valorResultado;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualización")
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_laboratorista")
    private LaboratoristaEntity laboratorista;

    public Long getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Long idResultado) {
        this.idResultado = idResultado;
    }

    public OrderDetailEntity getOrdenDetalle() {
        return ordenDetalle;
    }

    public void setOrdenDetalle(OrderDetailEntity ordenDetalle) {
        this.ordenDetalle = ordenDetalle;
    }

    public String getValorResultado() {
        return valorResultado;
    }

    public void setValorResultado(String valorResultado) {
        this.valorResultado = valorResultado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LaboratoristaEntity getLaboratorista() {
        return laboratorista;
    }

    public void setLaboratorista(LaboratoristaEntity laboratorista) {
        this.laboratorista = laboratorista;
    }
}
