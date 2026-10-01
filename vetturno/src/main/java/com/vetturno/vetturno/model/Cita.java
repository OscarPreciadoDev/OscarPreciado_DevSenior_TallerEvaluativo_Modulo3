package com.vetturno.vetturno.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;


// Esta clase representa una entidad, y una tabla en la base de datos, por eso se relaciona con @Entity y @Table
@Entity
@Table(name = "citas")
public class Cita {

    // Id de la cita, se genera automáticamente con @GeneratedValue y se establece como clave primaria con
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // Campos del contrato

    // FechaHora de la cita
    private LocalDateTime fechaHora;

    // Motivo de la cita
    private String motivo;

    // Una cita tiene una mascota únicamente por eso se utiliza @ManyToOne y se establece la relación con la clase Mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // Una cita tiene un solo especialista, por eso se utiliza @ManyToOne y se establece la relación con la clase Veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // Constructor vacio solicitado por Spring
    public Cita() {}

    // Constructor con todos los campos
    public Cita(LocalDateTime fechaHora, String motivo, Mascota mascota, Veterinario veterinario) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascota = mascota;
        this.veterinario = veterinario;
    }

    // Getters & Setters de todos los campos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
