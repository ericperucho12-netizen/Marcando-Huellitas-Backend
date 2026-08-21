package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.repositories.DonacionRepository;
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
 * Pruebas unitarias para DonacionService.
 */
@ExtendWith(MockitoExtension.class)
public class DonacionServiceTest {

    @Mock
    private DonacionRepository repository;

    @InjectMocks
    private DonacionService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        Donacion mockEntidad = new Donacion();
        when(repository.save(any(Donacion.class))).thenReturn(mockEntidad);
        // WHEN
        Donacion resultado = service.guardar(new Donacion());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Donacion.class));
    }
}
