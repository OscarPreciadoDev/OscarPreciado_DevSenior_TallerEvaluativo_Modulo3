package com.vetturno.vetturno.service;

import com.vetturno.vetturno.model.Cita;
import com.vetturno.vetturno.model.Mascota;
import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.model.Veterinario;
import com.vetturno.vetturno.repository.CitaRepository;
import com.vetturno.vetturno.repository.MascotaRepository;
import com.vetturno.vetturno.repository.VeterinarioRepository;

import java.time.LocalDateTime;

public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    // Metodo para guardar una cita

    public Cita crearCita(Cita cita) {

        resolverMascota(cita);
        resolverVeterinario(cita);
        resolverHora(cita);
        resolverVeterinarioLibre(cita);


        return citaRepository.save(cita);
    }

    // Resolver mascota
    public void resolverMascota(Cita cita) {

        // Verifica que la mascota no sea nula en la entrada
        if (cita.getMascota() != null) {

            // Busca a la mascota en la BD por su id
            Mascota mascota = mascotaRepository
                    .findById(cita.getMascota().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Mascota no encontrada"));  // Si no se encuentra, lanza una excepción
            cita.setMascota(mascota);                                        // Si se encuentra, establece la mascota en la cita
        }
    }

    // Resolver veterinario
    public void resolverVeterinario(Cita cita) {
        if (cita.getVeterinario() != null) {
            Veterinario veterinario = veterinarioRepository
                    .findById(cita.getVeterinario().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Veterinario no encontrado"));
            cita.setVeterinario(veterinario);
        }
    }

    // Resolver hora
    public void resolverHora(Cita cita) {
        if (cita.getFechaHora() == null) {
            throw new RuntimeException("La hora de la cita no puede ser nula");
        } else if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("La hora de la cita no puede ser anterior a la fecha y hora actual");
        }
    }

    // Resolver veterinario libre
    public void resolverVeterinarioLibre(Cita cita) {

        // Implementar lógica para verificar si el veterinario está libre en la hora solicitada
        if (citaRepository.existsByVeterinarioIdAndFechaHora(
                cita.getVeterinario().getId(),
                cita.getFechaHora())) {

            throw new RuntimeException(
                    "El veterinario ya tiene una cita en esa fecha y hora"
            );
        }
    }
}

