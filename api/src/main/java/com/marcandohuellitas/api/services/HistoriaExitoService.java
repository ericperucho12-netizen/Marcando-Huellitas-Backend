package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.repositories.HistoriaExitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaExitoService {

    private final HistoriaExitoRepository historiaExitoRepository;

    @Autowired
    public HistoriaExitoService(HistoriaExitoRepository historiaExitoRepository) {
        this.historiaExitoRepository = historiaExitoRepository;
    }

    public List<HistoriaExito> obtenerTodas() {
        return historiaExitoRepository.findAll();
    }

    public Optional<HistoriaExito> obtenerPorId(Long id) {
        return historiaExitoRepository.findById(id);
    }

    public HistoriaExito guardar(HistoriaExito historiaExito) {
        return historiaExitoRepository.save(historiaExito);
    }

    public void eliminar(Long id) {
        historiaExitoRepository.deleteById(id);
    }
}