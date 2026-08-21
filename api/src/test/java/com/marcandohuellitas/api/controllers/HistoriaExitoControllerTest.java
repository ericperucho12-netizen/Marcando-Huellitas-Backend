package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.services.HistoriaExitoService;
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
 * Pruebas unitarias para HistoriaExitoController.
 */
@ExtendWith(MockitoExtension.class)
public class HistoriaExitoControllerTest {

    @Mock
    private HistoriaExitoService service;

    @InjectMocks
    private HistoriaExitoController controller;

    @Test
    void listarTodos_DebeRetornarLista() {
        // GIVEN
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new HistoriaExito()));
        // WHEN
        List<HistoriaExito> resultado = controller.listarTodos();
        // THEN
        assertEquals(1, resultado.size());
    }
}
