package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.SolicitudesApoyo;
import com.marcandohuellitas.api.repositories.SolicitudesApoyoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para SolicitudesApoyo.
 * Aquí va la lógica de negocio.
 */
@Service
public class SolicitudesApoyoService {

    @Autowired // Inyectamos el repositorio
    private SolicitudesApoyoRepository repository;

    public List<SolicitudesApoyo> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<SolicitudesApoyo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public SolicitudesApoyo guardar(SolicitudesApoyo entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
