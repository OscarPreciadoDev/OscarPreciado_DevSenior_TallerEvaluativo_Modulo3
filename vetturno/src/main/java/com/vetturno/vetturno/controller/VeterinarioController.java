package com.vetturno.vetturno.controller;


import com.vetturno.vetturno.model.Veterinario;
import com.vetturno.vetturno.service.VeterinarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @PostMapping
    public Veterinario crearVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioService.crearVeterinario(veterinario);
    }
}
