package com.vetturno.vetturno.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "propietarios")
public class Propietario {

    // Id del propietario, se genera automáticamente con @GeneratedValue y se establece como clave primaria
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // Campos del contrato

    // Nombre del propietario
    private String nombre;

    // Teléfono del propietario
    private String telefono;

    // Email del propietario
    private String email;

    //  Lista de mascotas del propietario, un propietario puede tener muchas mascotas, por eso se utiliza @OneToMany y se establece la relación con la clase Mascota
    @OneToMany(mappedBy = "propietario")
    private List<Mascota> mascotas = new ArrayList<>();

    // Constructor vacío solicitado por spring
    public Propietario() {}

    // Constructor completo con todos los campos

    public Propietario(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
