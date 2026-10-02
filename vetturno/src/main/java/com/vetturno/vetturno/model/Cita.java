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

    @Column(name = "mascota_id")
    private Long mascotaId;

    @Column(name = "veterinario_id")
    private Long veterinarioId;

    // Constructor vacio solicitado por Spring
    public Cita() {}

    // Constructor con todos los campos
    public Cita(LocalDateTime fechaHora, String motivo, Long mascotaId, Long veterinarioId) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
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

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }
}
