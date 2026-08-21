package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para SolicitudesApoyo.
 * Esta clase representa la tabla solicitudes_apoyo en la base de datos.
 */
@Entity
@Table(name = "solicitudes_apoyo")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudesApoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "refugio_id")
    private Long refugioId;

    @Column(name = "tipo_apoyo")
    private String tipoApoyo;

    private String mensaje;

    private String estado;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}
