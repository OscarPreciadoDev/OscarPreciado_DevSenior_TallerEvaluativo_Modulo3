package com.vetturno.vetturno.controller;

import com.vetturno.vetturno.dto.MascotaDTO;
import com.vetturno.vetturno.dto.MascotaRequest;
import com.vetturno.vetturno.model.Mascota;
import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {

    // INYECCIÓN DE DEPENDENCIAS
    private final MascotaService mascotaService;

    // Constructor para inyectar la dependencia de MascotaService
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // METODOS QUE DISPONE ESTE CONTROLLER
    @GetMapping("/listar")
    public ResponseEntity<List<MascotaDTO>> obtenerMascotas() {

        // Creará una lista con MascotaDTO a partir de la lista de Mascotas obtenida del servicio
        List<MascotaDTO> respuesta = mascotaService.listarMascotas()
                .stream()
                .map(MascotaDTO::new)
                .collect(Collectors.toList());

        // Retorna la lista de MascotaDTO con un estado HTTP 200 OK
        return ResponseEntity.ok(respuesta);
    }

    // Metodo para crear una nueva mascota
    @PostMapping("/crear")
    public ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaRequest request) {

        // Crea un objeto Mascota a partir de los datos del request
        Mascota mascota = new Mascota();

        // Asigna los valores del request a la mascota
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());

        // Crea un objeto Propietario
        Propietario propietario = new Propietario();

        // Asigna el ID del propietario desde el request
        propietario.setId(request.getPropietarioId());
        mascota.setPropietario(propietario);

        // Llama al servicio para crear la mascota y obtiene la mascota guardada
        Mascota guardada = mascotaService.crearMascota(mascota);
        MascotaDTO respuesta = new MascotaDTO(guardada);

        // Retorna la mascota creada con un estado HTTP 201 Created
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }
}
