package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Producto.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
