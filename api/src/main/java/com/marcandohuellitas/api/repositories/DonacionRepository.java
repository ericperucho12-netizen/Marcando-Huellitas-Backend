package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Donacion.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}
