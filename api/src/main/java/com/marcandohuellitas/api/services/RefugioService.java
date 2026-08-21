package com.marcandohuellitas.api.services;


import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.repositories.RefugioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefugioService {
    @Autowired
     private RefugioRepository repository;

    public List<Refugio> obtenerTodos(){

        return repository.findAll();
    }
    public Optional<Refugio> obtenerPorId(Long id){

        return repository.findById(id);
    }
    public Refugio guardar(Refugio entidad){

        return repository.save(entidad);
    }
    public void eliminar(Long id){

        repository.deleteById(id);
    }
}
