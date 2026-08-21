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

    public Optional<HistoriaExito> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistoriaExito guardar(HistoriaExito entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
