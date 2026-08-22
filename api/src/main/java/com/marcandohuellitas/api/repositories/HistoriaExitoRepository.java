package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.HistoriaExito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriaExitoRepository extends JpaRepository<HistoriaExito, Long> {
    List<HistoriaExito> findByUsuarioId(Long usuarioId);
    List<HistoriaExito> findByMascotaId(Long mascotaId);
}