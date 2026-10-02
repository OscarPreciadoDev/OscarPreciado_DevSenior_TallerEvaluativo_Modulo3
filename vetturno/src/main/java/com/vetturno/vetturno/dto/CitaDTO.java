package com.vetturno.vetturno.dto;

import com.vetturno.vetturno.model.Cita;

import java.time.LocalDateTime;

public class CitaDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Long mascotaId;
    private Long veterinarioId;

    public CitaDTO() {}

    public CitaDTO(Cita cita) {

        this.id = cita.getId();
        this.fechaHora = cita.getFechaHora();
        this.motivo = cita.getMotivo();
        this.mascotaId = cita.getMascotaId();
        this.veterinarioId = cita.getVeterinarioId();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Long getMascotaId() {
        return mascotaId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }
}
