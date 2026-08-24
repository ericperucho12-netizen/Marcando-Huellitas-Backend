package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.MetodosPagoUsuario;
import com.marcandohuellitas.api.repositories.MetodosPagoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para MetodosPagoUsuario.
 * Aquí va la lógica de negocio.
 */
@Service
public class MetodosPagoUsuarioService {

    @Autowired // Inyectamos el repositorio
    private MetodosPagoUsuarioRepository repository;

    public List<MetodosPagoUsuario> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<MetodosPagoUsuario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public MetodosPagoUsuario guardar(MetodosPagoUsuario entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
