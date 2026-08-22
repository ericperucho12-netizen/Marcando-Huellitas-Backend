package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.SolicitudApoyo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudApoyoRepository extends JpaRepository<SolicitudApoyo, Long> {
}
