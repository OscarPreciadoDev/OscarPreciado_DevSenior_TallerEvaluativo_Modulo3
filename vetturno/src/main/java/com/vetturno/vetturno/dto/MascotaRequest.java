package com.vetturno.vetturno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MascotaRequest {

    // Nombre obligatorio
    @NotBlank(message = "El nombre no puede ir vacío")
    private String nombre;

    // Especie obligatoria
    @NotBlank(message = "La especie no puede ir vacía")
    private String especie;

    // No declara raza obligatoria
    private String raza;

    // Propietario obligatorio
    @NotNull(message = "El Id del propietario no puede ir vacío" )
    private Long propietarioId;

    public MascotaRequest() {}

    public MascotaRequest(String nombre, String especie, String raza, Long propietarioId) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.propietarioId = propietarioId;
    }

    // Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Long propietarioId) {
        this.propietarioId = propietarioId;
    }
}
