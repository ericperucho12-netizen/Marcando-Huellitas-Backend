package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Pedido;
import com.marcandohuellitas.api.repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService service;

    @Test
    void guardar_Exito(){
        Pedido mockEntidad = new Pedido();
        when(repository.save(any(Pedido.class))).thenReturn(mockEntidad);

        Pedido resultado = service.guardar(new Pedido());
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Pedido.class));
    }
}
