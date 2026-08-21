package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefugioRepository extends JpaRepository<Refugio, Long> {
}
