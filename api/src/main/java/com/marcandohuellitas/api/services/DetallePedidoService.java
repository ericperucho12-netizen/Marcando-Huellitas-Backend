package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.DetallePedido;
import com.marcandohuellitas.api.repositories.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {
    @Autowired
    private DetallePedidoRepository repository;

    public List<DetallePedido> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<DetallePedido> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public DetallePedido guardar(DetallePedido entidad) {
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
