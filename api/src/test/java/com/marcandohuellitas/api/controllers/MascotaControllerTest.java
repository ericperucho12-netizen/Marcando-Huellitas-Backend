package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.services.MascotaService;
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
public class MascotaControllerTest {
    @Mock
    private MascotaService service;
    @InjectMocks
    private MascotaController controller;

    @Test
    void listarTodos_DebeRetornarLista(){
        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new Mascota()));
        List<Mascota> resultado = controller.listarTodos();
        assertEquals(1,resultado.size());
    }
}
