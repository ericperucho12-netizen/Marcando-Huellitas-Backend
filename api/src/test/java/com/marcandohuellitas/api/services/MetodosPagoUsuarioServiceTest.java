package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.MetodosPagoUsuario;
import com.marcandohuellitas.api.repositories.MetodosPagoUsuarioRepository;
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
 * Pruebas unitarias para MetodosPagoUsuarioService.
 */
@ExtendWith(MockitoExtension.class)
public class MetodosPagoUsuarioServiceTest {

    @Mock
    private MetodosPagoUsuarioRepository repository;

    @InjectMocks
    private MetodosPagoUsuarioService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        MetodosPagoUsuario mockEntidad = new MetodosPagoUsuario();
        when(repository.save(any(MetodosPagoUsuario.class))).thenReturn(mockEntidad);
        // WHEN
        MetodosPagoUsuario resultado = service.guardar(new MetodosPagoUsuario());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(MetodosPagoUsuario.class));
    }
}
