package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.repositories.DireccionesUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para DireccionesUsuario.
 * Aquí va la lógica de negocio.
 */
@Service
public class DireccionesUsuarioService {

    @Autowired // Inyectamos el repositorio
    private DireccionesUsuarioRepository repository;

    public List<DireccionesUsuario> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<DireccionesUsuario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public DireccionesUsuario guardar(DireccionesUsuario entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
