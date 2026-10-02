package com.vetturno.vetturno.dto;

public class AuthResponse {

    // token correspondiente al proceso de devolución de una autorización.

    private String token;

    // Constructor

    public AuthResponse(String token) {
        this.token = token;
    }

    // getter

    public String getToken() {
        return token;
    }
}
