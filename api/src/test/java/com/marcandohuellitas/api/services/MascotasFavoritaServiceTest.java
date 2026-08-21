package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.MascotasFavorita;
import com.marcandohuellitas.api.repositories.MascotasFavoritaRepository;
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
 * Pruebas unitarias para MascotasFavoritaService.
 */
@ExtendWith(MockitoExtension.class)
public class MascotasFavoritaServiceTest {

    @Mock
    private MascotasFavoritaRepository repository;

    @InjectMocks
    private MascotasFavoritaService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        MascotasFavorita mockEntidad = new MascotasFavorita();
        when(repository.save(any(MascotasFavorita.class))).thenReturn(mockEntidad);
        // WHEN
        MascotasFavorita resultado = service.guardar(new MascotasFavorita());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(MascotasFavorita.class));
    }
}
