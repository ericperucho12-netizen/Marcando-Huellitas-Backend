package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.MascotasFavorita;
import com.marcandohuellitas.api.repositories.MascotasFavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para MascotasFavorita.
 * Aquí va la lógica de negocio.
 */
@Service
public class MascotasFavoritaService {

    @Autowired // Inyectamos el repositorio
    private MascotasFavoritaRepository repository;

    public List<MascotasFavorita> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<MascotasFavorita> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public MascotasFavorita guardar(MascotasFavorita entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
