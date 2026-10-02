package com.vetturno.vetturno.dto;

import com.vetturno.vetturno.model.Cita;

import java.time.LocalDateTime;

public class CitaDTO {

    // La salida muestra la cita de manera plana datos comprensibles de la mascota, su responsable y el veterinario
    // id, fechaHora, motivo, mascota, propietario, veterinario

    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Long mascotaId;
    private String mascotaNombre;
    private String propietarioNombre;
    private Long VeterinarioId;

    public CitaDTO() {}

    public CitaDTO(Cita cita) {

        this.id = cita.getId();
        this.fechaHora = cita.getFechaHora();
        this.motivo = cita.getMotivo();
        this.mascotaId = cita.getMascota().getId();
        this.mascotaNombre = cita.getMascota().getNombre();
        this.propietarioNombre =  cita.getMascota().getPropietario().getNombre();
        this.VeterinarioId =  cita.getVeterinario().getId();
    }
}
