package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository repository;
    public List<Mascota> obtenerTodos(){
        return repository.findAll();
    }
    public Optional<Mascota> obtenerPorId(Long id){
        return repository.findById(id);
    }
    public Mascota guardar(Mascota entidad){

        return repository.save(entidad);
    }
    public void eliminar(Long id){
        repository.deleteById(id);
    }
}
