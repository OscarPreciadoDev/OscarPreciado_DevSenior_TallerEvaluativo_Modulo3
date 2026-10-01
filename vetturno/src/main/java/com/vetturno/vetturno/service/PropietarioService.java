package com.vetturno.vetturno.service;

import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropietarioService {

    private final PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    // Implementación para listar propietarios
    public List<Propietario> listarPropietarios() {
        return propietarioRepository.findAll();
    }

    // Implementación para crear propietario
    public Propietario crearPropietario(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }
}
