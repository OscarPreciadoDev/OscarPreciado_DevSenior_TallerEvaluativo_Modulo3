package com.vetturno.vetturno.repository;

import com.vetturno.vetturno.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
