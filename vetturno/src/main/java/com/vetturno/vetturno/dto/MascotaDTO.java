package com.vetturno.vetturno.dto;

import com.vetturno.vetturno.model.Mascota;

public class MascotaDTO {

    // Atributos que debe tener la clase, estos son iguales a los que debe tener el model original

    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private Long propietarioId;
    private String propietario;


    public MascotaDTO() {
    }

    public MascotaDTO(Mascota mascota) {
        this.id = mascota.getId();
        this.nombre = mascota.getNombre();
        this.especie = mascota.getEspecie();
        this.raza = mascota.getRaza();
        this.propietarioId = mascota.getPropietario().getId();
        this.propietario = mascota.getPropietario().getNombre();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public String getRaza() {
        return raza;
    }

    public String getPropietario() {
        return propietario;
    }
}
