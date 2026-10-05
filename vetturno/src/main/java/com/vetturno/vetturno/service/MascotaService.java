package com.vetturno.vetturno.service;

import com.vetturno.vetturno.exception.BusinessException;
import com.vetturno.vetturno.model.Mascota;
import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.repository.MascotaRepository;
import com.vetturno.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MascotaService {

    //Implementación para listar y crear mascotas.

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    // Implementación para listar mascotas
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    // Implementación para crear mascotas
    public Mascota crearMascota(Mascota mascota) {
        resolverPropietario(mascota);
        return mascotaRepository.save(mascota);
    }

    // Implementación de solucionar propietario

    //Utiliza como argument una mascota (clase Mascota)
    private void resolverPropietario(Mascota mascota) {
        // Verifica que tenga un propietario
        if (mascota.getPropietario() != null) {

            // Lo busca en BD, toma su ID y lo guarda en una variable
            Propietario propietario = propietarioRepository
                    .findById(mascota.getPropietario().getId())
                    .orElseThrow(() ->
                        new BusinessException("Propietario no encontrado"));
            // Asigna el propietario encontrado a la mascota
            mascota.setPropietario(propietario);
        }
    }
}
