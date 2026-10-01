package com.vetturno.vetturno.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "veterinarios")
public class Veterinario {

    // Id del veterinario, se genera automáticamente con @GeneratedValue y se establece como clave primaria
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // Campos del contrato

    // Nombre del veterinario
    private String nombre;

    // Especialidad del veterinario
    private String especialidad;

    // Constructor vacío solicitado por Spring
    public Veterinario() {}

    // Constructor completo con todos los campos
    public Veterinario(String nombre, String especialidad){
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters & Setters de todos los campos


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
