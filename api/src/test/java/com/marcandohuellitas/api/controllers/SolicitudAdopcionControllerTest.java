package com.marcandohuellitas.api.controllers;


import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.services.RefugioService;
import com.marcandohuellitas.api.services.SolicitudAdopcionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SolicitudAdopcionControllerTest {
    @Mock
    private SolicitudAdopcionService service;
    @InjectMocks
    private SolicitudAdopcionController controller;

    @Test
    void listarTodos_DebeRetornarLista(){
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new SolicitudAdopcion()));
        List<SolicitudAdopcion> resultado = controller.listarTodos();
        assertEquals(1,resultado.size());
    }
}
