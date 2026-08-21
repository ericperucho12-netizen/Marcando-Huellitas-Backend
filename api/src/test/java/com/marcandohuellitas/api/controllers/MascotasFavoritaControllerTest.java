package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.MascotasFavorita;
import com.marcandohuellitas.api.services.MascotasFavoritaService;
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
 * Pruebas unitarias para MascotasFavoritaController.
 */
@ExtendWith(MockitoExtension.class)
public class MascotasFavoritaControllerTest {

    @Mock
    private MascotasFavoritaService service;

    @InjectMocks
    private MascotasFavoritaController controller;

    @Test
    void listarTodos_DebeRetornarLista() {
        // GIVEN
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new MascotasFavorita()));
        // WHEN
        List<MascotasFavorita> resultado = controller.listarTodos();
        // THEN
        assertEquals(1, resultado.size());
    }
}
