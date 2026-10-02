package com.vetturno.vetturno.controller;

import com.vetturno.vetturno.dto.PropietarioDTO;
import com.vetturno.vetturno.dto.PropietarioRequest;
import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.service.PropietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/propietarios")
public class PropietarioController {

    // INYECCIÓN DE DEPENDENCIAS
    private final PropietarioService propietarioService;

    // METODOS QUE DISPONE ESTE CONTROLLER
    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    // METODOS QUE DISPONE ESTE CONTROLLER

    // Endpoint para obtener la lista de propietarios
    @GetMapping("/listar")
    public ResponseEntity<List<PropietarioDTO>> obtenerPropietarios() {

        // Llamada al servicio para obtener la lista de propietarios y convertirla a DTOs
        List<PropietarioDTO> respuesta = propietarioService.listarPropietarios()
                .stream()
                .map(PropietarioDTO::new)
                .collect(Collectors.toList());

        // Retorna la respuesta con el estado HTTP 200 OK y la lista de propietarios
        return ResponseEntity.ok(respuesta);
    }

    // Endpoint para crear un nuevo propietario
    @PostMapping("/crear")
    public ResponseEntity<PropietarioDTO> crearPropietario(@RequestBody PropietarioRequest request) {

        // Crea un nuevo objeto Propietario a partir de los datos del request
        Propietario propietario = new Propietario();
        propietario.setNombre(request.getNombre());
        propietario.setTelefono(request.getTelefono());
        propietario.setEmail(request.getEmail());

        // Llamada al servicio para guardar el propietario y obtener el objeto guardado
        Propietario guardado = propietarioService.crearPropietario(propietario);

        // Crea un DTO a partir del objeto guardado
        PropietarioDTO respuesta = new PropietarioDTO(guardado);

        // Retorna la respuesta con el estado HTTP 201 Created y el DTO del propietario creado
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }
}
