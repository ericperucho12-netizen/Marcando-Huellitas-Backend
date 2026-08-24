package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Refugio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Refugio.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface RefugioRepository extends JpaRepository<Refugio, Long> {
}
