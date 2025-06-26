package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicos")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_medico;

    @JsonIgnoreProperties({"medico", "laboratorista", "paciente"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UserEntity userEntity;

    private String especialidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", updatable = false)
    private Date fecha_creacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fecha_actualizacion;

    // Getters y setters

    public Long getId_medico() {
        return id_medico;
    }
    public void setId_medico(Long id_medico) {
        this.id_medico = id_medico;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    @PrePersist
    protected void onCreate() {
        fecha_creacion = new Date();
        fecha_actualizacion = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        fecha_actualizacion = new Date();
    }
}
