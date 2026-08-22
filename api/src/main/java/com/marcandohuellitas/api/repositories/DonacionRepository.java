package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

}
