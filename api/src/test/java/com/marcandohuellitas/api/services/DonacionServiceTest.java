package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.repositories.DonacionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonacionServiceTest {

    @Mock
    private DonacionRepository donacionRepository;

    @InjectMocks
    private DonacionService donacionService;

    private Donacion donacion;

    @BeforeEach
    void setUp() {

        donacion = new Donacion();

        donacion.setId(1L);
        donacion.setNombreDonante("Adriana");
        donacion.setCorreoDonante("adriana@email.com");
        donacion.setMonto(new BigDecimal("500.00"));
        donacion.setFrecuencia("Única");
        donacion.setMetodoPago("TRANSFERENCIA");
        donacion.setEstado("COMPLETADA");
    }
}