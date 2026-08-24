package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.HistoriaExito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para HistoriaExito.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface HistoriaExitoRepository extends JpaRepository<HistoriaExito, Long> {
    java.util.List<HistoriaExito> findByEstado(String estado);
}
