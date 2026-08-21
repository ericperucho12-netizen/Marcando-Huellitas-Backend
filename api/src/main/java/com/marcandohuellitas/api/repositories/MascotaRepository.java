package com.marcandohuellitas.api.repositories;

import com.marcandohuellitas.api.models.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long>{

}
