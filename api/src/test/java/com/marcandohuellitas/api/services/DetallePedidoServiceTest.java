package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DetallePedido;
import com.marcandohuellitas.api.repositories.DetallePedidoRepository;
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
 * Pruebas unitarias para DetallePedidoService.
 */
@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository repository;

    @InjectMocks
    private DetallePedidoService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        DetallePedido mockEntidad = new DetallePedido();
        when(repository.save(any(DetallePedido.class))).thenReturn(mockEntidad);
        // WHEN
        DetallePedido resultado = service.guardar(new DetallePedido());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(DetallePedido.class));
    }
}
