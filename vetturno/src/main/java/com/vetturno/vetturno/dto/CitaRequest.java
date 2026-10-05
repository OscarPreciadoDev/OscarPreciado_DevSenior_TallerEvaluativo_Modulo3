package com.vetturno.vetturno.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CitaRequest {

    // Fecha obligatoria y futura
    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;

    // Motivo obligatorio
    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @NotNull(message = "El ID mascota no puede ir vacío")
    private Long mascotaId;

    @NotNull(message = "El ID veterinario no puede ir vacio")
    private Long veterinarioId;

    public CitaRequest() {}

    public CitaRequest(LocalDateTime fechaHora, String motivo, Long mascotaId, Long veterinarioId) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
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
