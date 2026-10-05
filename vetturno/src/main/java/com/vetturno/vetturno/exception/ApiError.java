package com.vetturno.vetturno.exception;

// Manejo global de errores (molde)

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {

    // Tiene un status
    private int status;

    // Mensaje
    private String mensaje;

    // Listado de errores
    private Map<String, String> errores;

    // Hora de error
    private String timestamp;

    // Constructor
    public ApiError(int status, String mensaje, Map<String, String> errores) {
        this.status = status;
        this.mensaje = mensaje;
        this.errores = errores;
        this.timestamp = LocalDateTime.now().toString();
    }

    //getters
    public int getStatus(){ return status; }
    public String getMensaje(){ return mensaje; }
    public Map<String, String> getErrores(){ return errores; }
    public String getTimestamp(){ return timestamp; }
}