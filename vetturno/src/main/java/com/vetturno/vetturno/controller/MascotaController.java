package com.vetturno.vetturno.controller;

import com.vetturno.vetturno.dto.MascotaDTO;
import com.vetturno.vetturno.model.Mascota;
import com.vetturno.vetturno.service.MascotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> obtenerMascotas() {
        return mascotaService.listarMascotas();
    }

    @PostMapping("/crear")
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.crearMascota(mascota);
    }
}
