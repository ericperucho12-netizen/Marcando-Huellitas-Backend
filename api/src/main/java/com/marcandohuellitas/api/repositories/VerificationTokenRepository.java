package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.VerificationToken;
import com.marcandohuellitas.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUsuario(Usuario usuario);
}
