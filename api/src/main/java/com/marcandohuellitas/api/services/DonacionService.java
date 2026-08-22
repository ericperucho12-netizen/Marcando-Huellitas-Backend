package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.repositories.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonacionService {

    // Service usa el repo que conecta a la BD
    @Autowired
    private DonacionRepository donacionRepository;

    // CREATE - Registrar una donación
    public Donacion registrarDonacion(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    // READ - Obtener todas las donaciones
    public List<Donacion> obtenerDonaciones() {
        return donacionRepository.findAll();
    }

    // READ - Buscar una donación por ID
    public Donacion obtenerDonacionPorId(Long id) {
        return donacionRepository.findById(id) // Busca una donación especifica
                .orElseThrow(() ->   //Genera el error después de encontrarlo
                        new RuntimeException("Donación no encontrada"));
    }

    // UPDATE - Actualizar una donación
    public Donacion actualizarDonacion(Long id, Donacion datosActualizados) {

        // Primero buscamos la donación existente
        Donacion donacionExistente = donacionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Donación no encontrada"));

        // Actualizamos sus datos
        donacionExistente.setNombreDonante(datosActualizados.getNombreDonante());
        donacionExistente.setCorreoDonante(datosActualizados.getCorreoDonante());
        donacionExistente.setTelefonoDonante(datosActualizados.getTelefonoDonante());
        donacionExistente.setMonto(datosActualizados.getMonto());
        donacionExistente.setFrecuencia(datosActualizados.getFrecuencia());
        donacionExistente.setMetodoPago(datosActualizados.getMetodoPago());
        donacionExistente.setComprobanteUrl(datosActualizados.getComprobanteUrl());
        donacionExistente.setEstado(datosActualizados.getEstado());
        donacionExistente.setUsuario(datosActualizados.getUsuario());

        // Guardamos los cambios
        return donacionRepository.save(donacionExistente);
    }

    // DELETE - Eliminar una donación por ID
    public void eliminarDonacion(Long id) {

        if (!donacionRepository.existsById(id)) { //Si NO existe una donación con ese ID, lanza un error.
            throw new RuntimeException("Donación no encontrada");
        }

        donacionRepository.deleteById(id);
    }
}