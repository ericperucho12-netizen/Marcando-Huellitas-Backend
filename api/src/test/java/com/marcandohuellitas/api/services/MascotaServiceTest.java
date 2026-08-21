package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.repositories.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;

/**
 * Pruebas unitarias para MascotaService.
 */
@ExtendWith(MockitoExtension.class)
public class MascotaServiceTest {

    @Mock
    private MascotaRepository repository;

    @InjectMocks
    private MascotaService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        Mascota mockEntidad = new Mascota();
        when(repository.save(any(Mascota.class))).thenReturn(mockEntidad);
        // WHEN
        Mascota resultado = service.guardar(new Mascota());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Mascota.class));
    }
}
