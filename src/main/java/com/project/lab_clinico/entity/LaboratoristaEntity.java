package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "laboratorista")
public class LaboratoristaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_laboratorista;

    @JsonIgnoreProperties({"medico", "laboratorista", "paciente"})
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UserEntity userEntity;

    private String turno;
    private String email;
    private String contraseña;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_actualizacion;

    // Getters y setters

    public Long getId_laboratorista() {
        return id_laboratorista;
    }

    public void setId_laboratorista(Long id_laboratorista) {
        this.id_laboratorista = id_laboratorista;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
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
