package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeContactoRepository extends JpaRepository<MensajeContacto, Long> {
}
