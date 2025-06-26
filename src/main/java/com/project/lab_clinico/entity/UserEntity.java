package com.project.lab_clinico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;
    private String sexo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TypeUser tipoUsuario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fecha_creacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fecha_actualizacion;




    // Campo transient para recibir datos de medico en request, NO se guarda ni serializa en response

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //@JsonManagedReference
    //  @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private MedicoEntity medico;

    // Relación OneToMany a laboratoristas (muchos laboratoristas por usuario)
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LaboratoristaEntity laboratorista;


    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
  //  @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @JsonBackReference
    private PacientEntity paciente;



    // Getters y Setters

    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fecha_nacimiento) {
        this.fechaNacimiento = fecha_nacimiento;
    }
    public TypeUser getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TypeUser tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }
    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }



    public MedicoEntity getMedico() {
        if (this.tipoUsuario == TypeUser.MEDICO) {
            return medico;
        }
        return null;
    }
    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }



    public LaboratoristaEntity getLaboratorista() {
        if (this.tipoUsuario == TypeUser.LABORATORISTA) {
            return laboratorista;
        }
        return null;
    }

    public void setLaboratorista(LaboratoristaEntity laboratorista) {
        this.laboratorista = laboratorista;
    }


    public PacientEntity getPaciente() {
        if (this.tipoUsuario == TypeUser.PACIENTE) {
            return paciente;
        }
        return null;
    }

    public void setPaciente(PacientEntity paciente) {
        this.paciente = paciente;
    }

    // Manejo automático fechas creación y actualización
    @PrePersist
    protected void onCreate() {
        fecha_creacion = LocalDateTime.now();
        fecha_actualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fecha_actualizacion = LocalDateTime.now();
    }
}
