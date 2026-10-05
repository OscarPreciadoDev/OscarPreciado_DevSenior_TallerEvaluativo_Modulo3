package com.vetturno.vetturno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistroRequest {

    // El registro debe llevar un email obligatoriamente
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    // Establece reglas para la contraseña
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;


    // getters y setters

    public @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no tiene un formato válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no tiene un formato válido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password) {
        this.password = password;
    }
}
