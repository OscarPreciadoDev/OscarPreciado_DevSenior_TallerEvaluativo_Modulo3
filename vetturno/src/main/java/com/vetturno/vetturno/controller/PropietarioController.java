package com.vetturno.vetturno.controller;

import com.vetturno.vetturno.model.Mascota;
import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.service.PropietarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @GetMapping
    public List<Propietario> obtenerPropietarios() {
        return propietarioService.listarPropietarios();
    }

    @PostMapping("/crear")
    public Propietario crearPropietario(@RequestBody Propietario propietario) {
        return propietarioService.crearPropietario(propietario);
    }
}
