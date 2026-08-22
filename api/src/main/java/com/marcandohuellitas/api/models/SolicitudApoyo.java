package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_apoyo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudApoyo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id")
    private BigDecimal usuarioId;
    @Column(name = "tipo_apoyo")
    private String tipoApoyo;
    private String mensaje;
    private String estado;
    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;
    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private  LocalDateTime actualizadoEn;

}
