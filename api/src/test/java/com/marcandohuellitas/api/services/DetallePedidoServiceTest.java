package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DetallePedido;
import com.marcandohuellitas.api.repositories.DetallePedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {
    @Mock
    private DetallePedidoRepository repository;

    @InjectMocks
    private DetallePedidoService service;

    @Test
    void guardar_Exito() {
        DetallePedido mockEntidad = new DetallePedido();
        when(repository.save(any(DetallePedido.class))).thenReturn(mockEntidad);

        DetallePedido resultado = service.guardar(new DetallePedido());
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(DetallePedido.class));
    }
}
