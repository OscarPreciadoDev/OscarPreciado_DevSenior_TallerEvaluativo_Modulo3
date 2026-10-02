package com.vetturno.vetturno.controller;

import com.vetturno.vetturno.dto.CitaDTO;
import com.vetturno.vetturno.dto.CitaRequest;
import com.vetturno.vetturno.model.Cita;
import com.vetturno.vetturno.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/agendar")
    public ResponseEntity<CitaDTO> agendarCita(@RequestBody CitaRequest request) {
        Cita cita = new Cita(
                request.getFechaHora(),
                request.getMotivo(),
                request.getMascotaId(),
                request.getVeterinarioId());


        Cita guardada = citaService.crearCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CitaDTO(guardada));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        List<CitaDTO> respuesta = citaService.listarCitas()
                .stream()
                .map(CitaDTO::new)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<CitaDTO>> listarCitasPorVeterinario(
            @PathVariable Long veterinarioId) {
        List<CitaDTO> respuesta = citaService.listarCitasPorVeterinario(veterinarioId)
                .stream()
                .map(CitaDTO::new)
                .toList();
        return ResponseEntity.ok(respuesta);
    }
}
