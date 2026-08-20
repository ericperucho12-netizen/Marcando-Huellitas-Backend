package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de Usuarios.
 * En Spring Boot, esta Interfaz es la que nos permite hacer consultas a la Base de Datos
 * (como SELECT, INSERT, UPDATE, DELETE) SIN tener que escribir código SQL a mano.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Método mágico de Spring Data JPA.
     * Con solo poner "findByCorreo", Spring Boot escribe el query de SQL por detrás:
     * "SELECT * FROM usuarios WHERE correo = ?"
     * 
     * Retorna un Optional<Usuario> para evitar el odiado error "NullPointerException" si no encuentra el correo.
     */
    Optional<Usuario> findByCorreo(String correo);
}
