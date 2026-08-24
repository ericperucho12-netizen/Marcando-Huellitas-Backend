package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Producto;
import com.marcandohuellitas.api.repositories.ProductoRepository;
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
 * Pruebas unitarias para ProductoService.
 */
@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        Producto mockEntidad = new Producto();
        when(repository.save(any(Producto.class))).thenReturn(mockEntidad);
        // WHEN
        Producto resultado = service.guardar(new Producto());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Producto.class));
    }
}
