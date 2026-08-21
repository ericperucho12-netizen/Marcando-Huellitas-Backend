package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para MascotasFavorita.
 * Esta clase representa la tabla mascotas_favoritas en la base de datos.
 */
@Entity
@Table(name = "mascotas_favoritas")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class MascotasFavorita {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "mascota_id")
    private Long mascotaId;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}
