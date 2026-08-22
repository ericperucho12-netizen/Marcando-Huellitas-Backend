package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.repositories.HistoriaExitoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriaExitoServiceTest {

    @Mock
    private HistoriaExitoRepository historiaExitoRepository;

    @InjectMocks
    private HistoriaExitoService historiaExitoService;

    private HistoriaExito historia;

    @BeforeEach
    void setUp() {
        historia = HistoriaExito.builder()
                .id(1L)
                .titulo("Final feliz para Pelusa")
                .historia("Pelusa fue adoptada y ahora vive feliz.")
                .imagenUrl("http://example.com/pelusa.jpg")
                .build();
    }

    @Test
    void obtenerTodas_DebeRetornarListaDeHistorias() {
        when(historiaExitoRepository.findAll()).thenReturn(Arrays.asList(historia));

        List<HistoriaExito> resultado = historiaExitoService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Final feliz para Pelusa", resultado.get(0).getTitulo());
        verify(historiaExitoRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_CuandoExiste_DebeRetornarHistoria() {
        when(historiaExitoRepository.findById(1L)).thenReturn(Optional.of(historia));

        Optional<HistoriaExito> resultado = historiaExitoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Final feliz para Pelusa", resultado.get().getTitulo());
        verify(historiaExitoRepository, times(1)).findById(1L);
    }

    @Test
    void guardar_DebeGuardarYRetornarHistoria() {
        when(historiaExitoRepository.save(any(HistoriaExito.class))).thenReturn(historia);

        HistoriaExito guardada = historiaExitoService.guardar(historia);

        assertNotNull(guardada);
        assertEquals("Final feliz para Pelusa", guardada.getTitulo());
        verify(historiaExitoRepository, times(1)).save(historia);
    }

    @Test
    void eliminar_DebeLlamarAlMetodoDelete() {
        doNothing().when(historiaExitoRepository).deleteById(1L);

        historiaExitoService.eliminar(1L);

        verify(historiaExitoRepository, times(1)).deleteById(1L);
    }
}