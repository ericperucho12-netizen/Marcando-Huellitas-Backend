package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.SolicitudesApoyo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para SolicitudesApoyo.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface SolicitudesApoyoRepository extends JpaRepository<SolicitudesApoyo, Long> {
}
