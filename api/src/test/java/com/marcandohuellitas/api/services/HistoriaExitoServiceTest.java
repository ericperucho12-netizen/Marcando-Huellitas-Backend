package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.repositories.HistoriaExitoRepository;
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
 * Pruebas unitarias para HistoriaExitoService.
 */
@ExtendWith(MockitoExtension.class)
public class HistoriaExitoServiceTest {

    @Mock
    private HistoriaExitoRepository repository;

    @InjectMocks
    private HistoriaExitoService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        HistoriaExito mockEntidad = new HistoriaExito();
        when(repository.save(any(HistoriaExito.class))).thenReturn(mockEntidad);
        // WHEN
        HistoriaExito resultado = service.guardar(new HistoriaExito());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(HistoriaExito.class));
    }
}
