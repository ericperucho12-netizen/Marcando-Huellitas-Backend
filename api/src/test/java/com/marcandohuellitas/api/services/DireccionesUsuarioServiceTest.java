package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.repositories.DireccionesUsuarioRepository;
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
 * Pruebas unitarias para DireccionesUsuarioService.
 */
@ExtendWith(MockitoExtension.class)
public class DireccionesUsuarioServiceTest {

    @Mock
    private DireccionesUsuarioRepository repository;

    @InjectMocks
    private DireccionesUsuarioService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        DireccionesUsuario mockEntidad = new DireccionesUsuario();
        when(repository.save(any(DireccionesUsuario.class))).thenReturn(mockEntidad);
        // WHEN
        DireccionesUsuario resultado = service.guardar(new DireccionesUsuario());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(DireccionesUsuario.class));
    }
}
