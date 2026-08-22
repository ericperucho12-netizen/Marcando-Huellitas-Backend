package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.services.DireccionesUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DireccionesUsuarioControllerTest {
    @Mock
    private DireccionesUsuarioService service;

    @InjectMocks
    private DireccionesUsuarioController controller;

    @Test
    void listarTodos_DebeRetornarLista() {
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new DireccionesUsuario()));
        List<DireccionesUsuario> resultado = controller.listarTodos();
        assertEquals(1,resultado.size());
    }

}