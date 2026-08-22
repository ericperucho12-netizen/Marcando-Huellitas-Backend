package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.repositories.DireccionesUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DireccionesUsuarioServiceTest {
    @Mock
    private DireccionesUsuarioRepository repository;

    @InjectMocks
    private DireccionesUsuarioService service;

    @Test
    void guardar_Exito() {
        DireccionesUsuario mockEntidad = new DireccionesUsuario();
        when(repository.save(any(DireccionesUsuario.class))).thenReturn(mockEntidad);
        DireccionesUsuario resultado = service.guardar(new DireccionesUsuario());
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(DireccionesUsuario.class));
    }
}
