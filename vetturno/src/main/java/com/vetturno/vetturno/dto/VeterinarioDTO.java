package com.vetturno.vetturno.dto;

import com.vetturno.vetturno.model.Veterinario;

public class VeterinarioDTO {

    private Long id;
    private String nombre;
    private String especialidad;

    public VeterinarioDTO() {}

    public VeterinarioDTO(Veterinario veterinario) {
        this.id = veterinario.getId();
        this.nombre = veterinario.getNombre();
        this.especialidad = veterinario.getEspecialidad();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
