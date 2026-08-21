package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.repositories.RefugioRepository;
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
 * Pruebas unitarias para RefugioService.
 */
@ExtendWith(MockitoExtension.class)
public class RefugioServiceTest {

    @Mock
    private RefugioRepository repository;

    @InjectMocks
    private RefugioService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        Refugio mockEntidad = new Refugio();
        when(repository.save(any(Refugio.class))).thenReturn(mockEntidad);
        // WHEN
        Refugio resultado = service.guardar(new Refugio());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Refugio.class));
    }
}
