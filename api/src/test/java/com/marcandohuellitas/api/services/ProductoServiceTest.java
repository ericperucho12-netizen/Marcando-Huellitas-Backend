package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Producto;
import com.marcandohuellitas.api.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void guardar_Exito() {
        Producto mockEntidad = new Producto();
        when(repository.save(any(Producto.class))).thenReturn(mockEntidad);

        Producto resultado = service.guardar(new Producto());

        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Producto.class));
    }
}
