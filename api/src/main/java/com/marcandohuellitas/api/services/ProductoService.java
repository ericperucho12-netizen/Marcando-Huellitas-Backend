package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Producto;
import com.marcandohuellitas.api.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para Producto.
 * Aquí va la lógica de negocio.
 */
@Service
public class ProductoService {

    @Autowired // Inyectamos el repositorio
    private ProductoRepository repository;

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Producto guardar(Producto entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
