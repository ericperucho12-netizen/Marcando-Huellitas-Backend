package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.repositories.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para Donacion.
 * Aquí va la lógica de negocio.
 */
@Service
public class DonacionService {

    @Autowired // Inyectamos el repositorio
    private DonacionRepository repository;

    public List<Donacion> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Donacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Donacion guardar(Donacion entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
