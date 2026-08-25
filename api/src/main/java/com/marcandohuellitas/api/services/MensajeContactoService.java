package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.MensajeContacto;
import com.marcandohuellitas.api.repositories.MensajeContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeContactoService {

    @Autowired
    private MensajeContactoRepository repository;

    public MensajeContacto guardarMensaje(MensajeContacto mensaje) {
        mensaje.setEstado("NO_LEIDO");
        return repository.save(mensaje);
    }

    public List<MensajeContacto> obtenerTodos() {
        return repository.findAll();
    }

    public MensajeContacto actualizarEstado(Long id, String estado) {
        Optional<MensajeContacto> optionalMensaje = repository.findById(id);
        if (optionalMensaje.isPresent()) {
            MensajeContacto mensaje = optionalMensaje.get();
            mensaje.setEstado(estado);
            return repository.save(mensaje);
        }
        return null;
    }
}
