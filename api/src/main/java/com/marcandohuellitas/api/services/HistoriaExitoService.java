package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.repositories.HistoriaExitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para HistoriaExito.
 * Aquí va la lógica de negocio.
 */
@Service
public class HistoriaExitoService {

    @Autowired // Inyectamos el repositorio
    private HistoriaExitoRepository repository;

    public List<HistoriaExito> obtenerTodos() {
        return repository.findAll();
    }

    public List<HistoriaExito> obtenerAprobados() {
        return repository.findByEstado("APROBADO");
    }

    public Optional<HistoriaExito> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistoriaExito guardar(HistoriaExito entidad) {
        if (entidad.getEstado() == null || entidad.getEstado().isEmpty()) {
            entidad.setEstado("PENDIENTE");
        }
        return repository.save(entidad);
    }
    
    public HistoriaExito editarHistoria(Long id, HistoriaExito datosNuevos) {
        Optional<HistoriaExito> historiaOp = repository.findById(id);
        if (historiaOp.isPresent()) {
            HistoriaExito historia = historiaOp.get();
            historia.setTitulo(datosNuevos.getTitulo());
            historia.setHistoria(datosNuevos.getHistoria());
            historia.setImagenUrl(datosNuevos.getImagenUrl());
            return repository.save(historia);
        }
        return null;
    }

    public HistoriaExito actualizarEstado(Long id, String estado) {
        Optional<HistoriaExito> historiaOp = repository.findById(id);
        if (historiaOp.isPresent()) {
            HistoriaExito historia = historiaOp.get();
            historia.setEstado(estado.toUpperCase());
            return repository.save(historia);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}