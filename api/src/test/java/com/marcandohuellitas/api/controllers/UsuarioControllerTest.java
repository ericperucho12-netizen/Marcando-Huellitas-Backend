package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.AuthResponseDTO;
import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.security.JwtUtil;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioServices usuarioServices;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void login_DebeRetornar200_CuandoEsExitoso() {
        UsuarioLoginDTO loginDTO = new UsuarioLoginDTO();
        loginDTO.setCorreo("admin@huellitas.com");
        loginDTO.setPassword("12345");

        Usuario mockUser = new Usuario();
        mockUser.setCorreo("admin@huellitas.com");
        mockUser.setNombre("Admin");
        mockUser.setRol("ADMIN");

        when(usuarioServices.loginUsuario(anyString(), anyString())).thenReturn(mockUser);
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn("mock.jwt.token");

        ResponseEntity<?> respuesta = usuarioController.loginUsuario(loginDTO);

        assertEquals(200, respuesta.getStatusCode().value());
        AuthResponseDTO body = (AuthResponseDTO) respuesta.getBody();
        assertNotNull(body);
        assertEquals("mock.jwt.token", body.getToken());
        assertEquals("Admin", body.getUsuario().getNombre());
    }

    @Test
    void login_DebeRetornarError_CuandoFalla() {
        UsuarioLoginDTO loginDTO = new UsuarioLoginDTO();
        loginDTO.setCorreo("admin@huellitas.com");
        loginDTO.setPassword("mala");

        when(usuarioServices.loginUsuario(anyString(), anyString())).thenThrow(new RuntimeException("Credenciales incorrectas"));

        ResponseEntity<?> respuesta = usuarioController.loginUsuario(loginDTO);

        assertEquals(401, respuesta.getStatusCode().value());
        assertEquals("Credenciales incorrectas", respuesta.getBody());
    }
}