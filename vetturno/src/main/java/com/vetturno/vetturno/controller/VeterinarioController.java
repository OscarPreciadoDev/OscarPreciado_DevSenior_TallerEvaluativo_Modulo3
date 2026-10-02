package com.vetturno.vetturno.controller;


import com.vetturno.vetturno.dto.VeterinarioDTO;
import com.vetturno.vetturno.dto.VeterinarioRequest;
import com.vetturno.vetturno.model.Veterinario;
import com.vetturno.vetturno.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }


    // POST PARA CREAR UN NUEVO VETERINARIO
    @PostMapping("/crear")
    public ResponseEntity<VeterinarioDTO> crearVeterinario(@RequestBody VeterinarioRequest request) {       // Mapea un metodo a peticiones HTTP POST.

        // Crea un nuevo objeto Veterinario
        Veterinario veterinario = new Veterinario();

        // Asigna los valores del request al objeto Veterinario
        veterinario.setNombre(request.getNombre());
        veterinario.setEspecialidad(request.getEspecialidad());

        // Llama al servicio para crear el veterinario y guarda el resultado
        Veterinario guardado =
                veterinarioService.crearVeterinario(veterinario);

        // Crea un DTO de respuesta a partir del veterinario guardado
        VeterinarioDTO respuesta = new VeterinarioDTO(guardado);

        // Devuelve una respuesta HTTP con el estado CREATED y el DTO en el cuerpo
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    // GET PARA LISTAR TODOS LOS VETERINARIOS
    @GetMapping("/listar")

    // Mapea un metodo a peticiones HTTP GET.
    public ResponseEntity<List<VeterinarioDTO>> listarVeterinarios() {

        // Llama al servicio para obtener la lista de veterinarios, la convierte a DTOs y la devuelve en la respuesta HTTP
        List<VeterinarioDTO> respuesta = veterinarioService.listarVeterinarios()
                .stream()
                .map(VeterinarioDTO::new)
                .toList();

        // Devuelve una respuesta HTTP con el estado OK y la lista de DTOs en el cuerpo
        return ResponseEntity.ok(respuesta);
    }
}
