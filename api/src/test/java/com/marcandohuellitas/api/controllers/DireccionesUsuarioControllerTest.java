package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.services.DireccionesUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias para DireccionesUsuarioController.
 */
@ExtendWith(MockitoExtension.class)
public class DireccionesUsuarioControllerTest {

    @Mock
    private DireccionesUsuarioService service;

    @InjectMocks
    private DireccionesUsuarioController controller;

    @Test
    void listarTodos_DebeRetornarLista() {
        // GIVEN
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new DireccionesUsuario()));
        // WHEN
        List<DireccionesUsuario> resultado = controller.listarTodos();
        // THEN
        assertEquals(1, resultado.size());
    }
}
