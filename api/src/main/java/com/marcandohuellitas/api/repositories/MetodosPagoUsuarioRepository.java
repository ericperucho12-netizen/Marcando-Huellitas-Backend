package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.MetodosPagoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para MetodosPagoUsuario.
 * Nos permite acceder a la base de datos sin escribir SQL.
 */
@Repository
public interface MetodosPagoUsuarioRepository extends JpaRepository<MetodosPagoUsuario, Long> {
}
