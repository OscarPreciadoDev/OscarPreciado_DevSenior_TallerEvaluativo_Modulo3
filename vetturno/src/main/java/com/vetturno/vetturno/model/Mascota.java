package com.vetturno.vetturno.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "mascotas")
public class Mascota {

    // Id de la mascota, se genera automáticamente con @GeneratedValue y se establece como clave primaria
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // Campos del contrato

    // Nombre de la mascota
    private String nombre;

    // Especie de la mascota
    private String especie;

    // Raza de la mascota
    private String raza;

    // Propietario de la mascota (Utiliza la columna propietario_id para la relación de la tabla mascotas con la tabla propietarios)
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    // Constructor vacío solicitado por Spring

    public Mascota() {}

    // Constructor completo con todos los campos

    public Mascota(String nombre, String especie, String raza, Propietario propietario) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.propietario = propietario;
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
