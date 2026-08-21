package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.SolicitudesApoyo;
import com.marcandohuellitas.api.repositories.SolicitudesApoyoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para SolicitudesApoyoService.
 */
@ExtendWith(MockitoExtension.class)
public class SolicitudesApoyoServiceTest {

    @Mock
    private SolicitudesApoyoRepository repository;

    @InjectMocks
    private SolicitudesApoyoService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        SolicitudesApoyo mockEntidad = new SolicitudesApoyo();
        when(repository.save(any(SolicitudesApoyo.class))).thenReturn(mockEntidad);
        // WHEN
        SolicitudesApoyo resultado = service.guardar(new SolicitudesApoyo());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(SolicitudesApoyo.class));
    }
}
