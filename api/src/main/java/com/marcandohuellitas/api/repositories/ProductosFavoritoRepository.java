package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.ProductosFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para ProductosFavorito.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface ProductosFavoritoRepository extends JpaRepository<ProductosFavorito, Long> {
}
