package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para HistoriaExito.
 * Esta clase representa la tabla historias_exito en la base de datos.
 */
@Entity
@Table(name = "historias_exito")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaExito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mascota_id")
    private Long mascotaId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String titulo;

    private String historia;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "estado")
    private String estado = "PENDIENTE";
}

