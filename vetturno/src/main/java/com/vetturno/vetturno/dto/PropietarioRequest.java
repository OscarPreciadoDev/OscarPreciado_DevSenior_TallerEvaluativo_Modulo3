package com.vetturno.vetturno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PropietarioRequest {

    // Nombre obligatorio
    @NotBlank(message = "El nombre no puede ir vacío")
    private String nombre;

    // Teléfono obligatorio
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 10 , message = "El teléfono debe tener 10 cifras")
    private String telefono;

    // email valido
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    public PropietarioRequest() {}

    public PropietarioRequest(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters and Setters


    public @NotBlank(message = "El nombre no puede ir vacío") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre no puede ir vacío") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "El teléfono no puede ir vacío") @Size(min = 10, max = 10, message = "El teléfono debe tener 10 cifras") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotNull(message = "El teléfono no puede ir vacío") @Size(min = 10, max = 10, message = "El teléfono debe tener 10 cifras") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no tiene un formato válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no tiene un formato válido") String email) {
        this.email = email;
    }
}
