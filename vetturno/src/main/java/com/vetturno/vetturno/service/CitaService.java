package com.vetturno.vetturno.service;

import com.vetturno.vetturno.model.Cita;
import com.vetturno.vetturno.repository.CitaRepository;
import com.vetturno.vetturno.repository.MascotaRepository;
import com.vetturno.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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
        validarMascota(cita);
        validarVeterinario(cita);
        resolverHora(cita);
        resolverVeterinarioLibre(cita);

        return citaRepository.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public List<Cita> listarCitasPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId);
    }

    private void validarMascota(Cita cita) {
        if (cita.getMascotaId() == null
                || !mascotaRepository.existsById(cita.getMascotaId())) {
            throw new RuntimeException("Mascota no encontrada");
        }
    }

    private void validarVeterinario(Cita cita) {
        if (cita.getVeterinarioId() == null
                || !veterinarioRepository.existsById(cita.getVeterinarioId())) {
            throw new RuntimeException("Veterinario no encontrado");
        }
    }

    private void resolverHora(Cita cita) {
        if (cita.getFechaHora() == null) {
            throw new RuntimeException("La hora de la cita no puede ser nula");
        } else if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("La hora de la cita no puede ser anterior a la fecha y hora actual");
        }
    }

    private void resolverVeterinarioLibre(Cita cita) {
        if (citaRepository.existsByVeterinarioIdAndFechaHora(
                cita.getVeterinarioId(),
                cita.getFechaHora())) {

            throw new RuntimeException(
                    "El veterinario ya tiene una cita en esa fecha y hora"
            );
        }
    }
}
