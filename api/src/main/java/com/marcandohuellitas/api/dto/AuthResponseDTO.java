package com.marcandohuellitas.api.dto;

import com.marcandohuellitas.api.models.Usuario;

public class AuthResponseDTO {
    private String token;
    private Usuario usuario;

    public AuthResponseDTO(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}