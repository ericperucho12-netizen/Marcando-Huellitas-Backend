package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.services.SolicitudAdopcionService;
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
 * Pruebas unitarias para SolicitudAdopcionController.
 */
@ExtendWith(MockitoExtension.class)
public class SolicitudAdopcionControllerTest {

    @Mock
    private SolicitudAdopcionService service;

    @InjectMocks
    private SolicitudAdopcionController controller;

    @Test
    void listarTodos_DebeRetornarLista() {
        // GIVEN
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new SolicitudAdopcion()));
        // WHEN
        List<SolicitudAdopcion> resultado = controller.listarTodos();
        // THEN
        assertEquals(1, resultado.size());
    }
}
