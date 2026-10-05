package com.vetturno.vetturno.dto;

import jakarta.validation.constraints.NotBlank;

public class VeterinarioRequest {

    // Nombre obligatorio
    @NotBlank(message = "El nombre no puede ir vacío")
    private String nombre;

    // Especialidad obligatoria
    @NotBlank(message = "La especialidad no puede ir vacía")
    private String especialidad;

    public VeterinarioRequest() {}

    public VeterinarioRequest(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters and Setters

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
