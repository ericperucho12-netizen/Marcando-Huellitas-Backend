package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.SolicitudApoyo;
import com.marcandohuellitas.api.repositories.SolicitudApoyoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudApoyoService {
    @Autowired
    private SolicitudApoyoRepository repository;

    public List<SolicitudApoyo> obtenerTodos(){
        return repository.findAll();
    }

    public Optional<SolicitudApoyo> obtenerPorId(Long id){
        return repository.findById(id);
    }

    public SolicitudApoyo guardar(SolicitudApoyo entidad){
        return repository.save(entidad);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}
