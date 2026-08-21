package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.SolicitudAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para SolicitudAdopcion.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Long> {
}
