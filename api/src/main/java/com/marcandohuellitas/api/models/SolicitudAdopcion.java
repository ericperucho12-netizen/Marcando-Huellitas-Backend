package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para SolicitudAdopcion.
 * Esta clase representa la tabla solicitudes_adopcion en la base de datos.
 */
@Entity
@Table(name = "solicitudes_adopcion")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "mascota_id")
    private Long mascotaId;

    private String telefono;

    private String direccion;

    private String experiencia;

    private String estado;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}

