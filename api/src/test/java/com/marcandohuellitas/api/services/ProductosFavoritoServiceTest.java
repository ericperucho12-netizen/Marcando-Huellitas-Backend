package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.ProductosFavorito;
import com.marcandohuellitas.api.repositories.ProductosFavoritoRepository;
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
 * Pruebas unitarias para ProductosFavoritoService.
 */
@ExtendWith(MockitoExtension.class)
public class ProductosFavoritoServiceTest {

    @Mock
    private ProductosFavoritoRepository repository;

    @InjectMocks
    private ProductosFavoritoService service;

    @Test
    void guardar_Exito() {
        // GIVEN
        ProductosFavorito mockEntidad = new ProductosFavorito();
        when(repository.save(any(ProductosFavorito.class))).thenReturn(mockEntidad);
        // WHEN
        ProductosFavorito resultado = service.guardar(new ProductosFavorito());
        // THEN
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(ProductosFavorito.class));
    }
}
