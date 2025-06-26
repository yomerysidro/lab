package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Orden;

    @Column(name = "codigo_orden")
    private String codigoOrden;

    @Enumerated(EnumType.STRING)
    private StatusOrder estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacientEntity paciente;

    @ManyToOne
    @JoinColumn(name = "id_laboratorista")
    private LaboratoristaEntity laboratorista;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private MedicoEntity medico;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> detalles;

    public Long getIdOrden() {
        return Id_Orden;
    }

    public void setIdOrden(Long Id_Orden) {
        this.Id_Orden = Id_Orden;
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public StatusOrder getEstado() {
        return estado;
    }

    public void setEstado(StatusOrder estado) {
        this.estado = estado;
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

    public PacientEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacientEntity paciente) {
        this.paciente = paciente;
    }

    public LaboratoristaEntity getLaboratorista() {
        return laboratorista;
    }

    public void setLaboratorista(LaboratoristaEntity laboratorista) {
        this.laboratorista = laboratorista;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public List<OrderDetailEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrderDetailEntity> detalles) {
        this.detalles = detalles;
    }
}
