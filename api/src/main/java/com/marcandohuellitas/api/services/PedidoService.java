package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Pedido;
import com.marcandohuellitas.api.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para Pedido.
 * Aquí va la lógica de negocio.
 */
@Service
public class PedidoService {

    @Autowired // Inyectamos el repositorio
    private PedidoRepository repository;

    public List<Pedido> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Pedido guardar(Pedido entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
