package com.marcandohuellitas.api.controllers;


import com.marcandohuellitas.api.models.SolicitudApoyo;
import com.marcandohuellitas.api.services.SolicitudApoyoService;
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
public class SolicitudApoyoControllerTest {
    @Mock
    private SolicitudApoyoService service;

    @InjectMocks
    private SolicitudApoyoController controller;

    @Test
    void listarTodos_DebeRetornarLista(){
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new SolicitudApoyo()));
        List<SolicitudApoyo> resultado = controller.listarTodos();
        assertEquals(1, resultado.size());
    }
}
