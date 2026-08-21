package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.ProductosFavorito;
import com.marcandohuellitas.api.repositories.ProductosFavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para ProductosFavorito.
 * Aquí va la lógica de negocio.
 */
@Service
public class ProductosFavoritoService {

    @Autowired // Inyectamos el repositorio
    private ProductosFavoritoRepository repository;

    public List<ProductosFavorito> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<ProductosFavorito> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ProductosFavorito guardar(ProductosFavorito entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
