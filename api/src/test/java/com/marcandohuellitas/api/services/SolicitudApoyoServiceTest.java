package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.SolicitudApoyo;
import com.marcandohuellitas.api.repositories.SolicitudApoyoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitudApoyoServiceTest {
    @Mock
    private SolicitudApoyoRepository repository;

    @InjectMocks
    private SolicitudApoyoService service;

    @Test
    void guardar_Exito(){
        SolicitudApoyo mockEntidad = new SolicitudApoyo();
        when(repository.save(any(SolicitudApoyo.class))).thenReturn(mockEntidad);

        SolicitudApoyo resultado = service.guardar(new SolicitudApoyo());
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(SolicitudApoyo.class));
    }
}
