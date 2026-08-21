package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para DireccionesUsuario.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface DireccionesUsuarioRepository extends JpaRepository<DireccionesUsuario, Long> {
}
