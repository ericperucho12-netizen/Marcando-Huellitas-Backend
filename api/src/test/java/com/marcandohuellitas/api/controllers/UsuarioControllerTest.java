package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Pruebas para el Controlador de Usuarios.
 * En lugar de levantar el servidor web, probamos la clase directamente con Mockito
 * para que sea 100% rápido y no dependa de otras librerías externas.
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioServices usuarioServices;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void login_DebeRetornar200_CuandoEsExitoso() {
        // GIVEN
        UsuarioLoginDTO loginDTO = new UsuarioLoginDTO();
        loginDTO.setCorreo("admin@huellitas.com");
        loginDTO.setPassword("12345");

        when(usuarioServices.loginUsuario(anyString(), anyString())).thenReturn("Login exitoso");

        // WHEN
        ResponseEntity<?> respuesta = usuarioController.loginUsuario(loginDTO);

        // THEN
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("Login exitoso", respuesta.getBody());
    }

    @Test
    void login_DebeRetornar401_CuandoFalla() {
        // GIVEN
        UsuarioLoginDTO loginDTO = new UsuarioLoginDTO();
        loginDTO.setCorreo("admin@huellitas.com");
        loginDTO.setPassword("malaPass");

        when(usuarioServices.loginUsuario(anyString(), anyString())).thenReturn("Credenciales incorrectas. Intentos restantes: 4");

        // WHEN
        ResponseEntity<?> respuesta = usuarioController.loginUsuario(loginDTO);

        // THEN
        assertEquals(401, respuesta.getStatusCode().value());
        assertEquals("Credenciales incorrectas. Intentos restantes: 4", respuesta.getBody());
    }
}