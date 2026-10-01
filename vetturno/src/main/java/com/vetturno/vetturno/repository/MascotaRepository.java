package com.vetturno.vetturno.repository;

import com.vetturno.vetturno.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
