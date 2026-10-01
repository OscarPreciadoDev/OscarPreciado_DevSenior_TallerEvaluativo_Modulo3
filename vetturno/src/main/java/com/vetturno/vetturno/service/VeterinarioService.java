package com.vetturno.vetturno.service;

import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.model.Veterinario;
import com.vetturno.vetturno.repository.PropietarioRepository;
import com.vetturno.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    // Implementación para listar y crear veterinarios.

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    // Implementación para listar veterinarios
    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepository.findAll();
    }

    // Implementación para crear veterinario
    public Veterinario crearVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

}
