package com.vetturno.vetturno.repository;

import com.vetturno.vetturno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {


    // Consulta para listar citas por veterinario
    List<Cita> findByVeterinarioId(Long veterinarioId);

    // Consulta una cita por veterinario y fecha
    boolean existsByVeterinarioIdAndFechaHora(Long VeterinarioId, LocalDateTime fechaHora);

}
