package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.repositories.RefugioRepository;
import com.marcandohuellitas.api.repositories.SolicitudAdopcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudAdopcionService {
    @Autowired
    private SolicitudAdopcionRepository repository;

    public List<SolicitudAdopcion> obtenerTodos(){

        return repository.findAll();
    }
    public Optional<SolicitudAdopcion> obtenerPorId(Long id){

        return repository.findById(id);
    }
    public  SolicitudAdopcion guardar(SolicitudAdopcion entidad){

        return repository.save(entidad);
    }
    public void eliminar(Long id){

        repository.deleteById(id);
    }
}
