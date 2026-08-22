package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.repositories.RefugioRepository;
import com.marcandohuellitas.api.repositories.SolicitudAdopcionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitudAdopcionServiceTest {
    @Mock
    private SolicitudAdopcionRepository repository;

    @InjectMocks
    private SolicitudAdopcionService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        SolicitudAdopcion mockEntidad = new SolicitudAdopcion();
        when(repository.save(any(SolicitudAdopcion.class))).thenReturn(mockEntidad);
        // WHEN
        SolicitudAdopcion resultado = service.guardar(new SolicitudAdopcion());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(SolicitudAdopcion.class));
    }
}
