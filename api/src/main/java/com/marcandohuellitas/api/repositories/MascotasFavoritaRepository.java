package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.MascotasFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para MascotasFavorita.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface MascotasFavoritaRepository extends JpaRepository<MascotasFavorita, Long> {
}
