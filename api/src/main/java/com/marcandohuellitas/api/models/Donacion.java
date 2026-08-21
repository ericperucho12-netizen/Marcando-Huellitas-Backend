package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para Donacion.
 * Esta clase representa la tabla donaciones en la base de datos.
 */
@Entity
@Table(name = "donaciones")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nombre_donante")
    private String nombreDonante;

    @Column(name = "correo_donante")
    private String correoDonante;

    @Column(name = "telefono_donante")
    private String telefonoDonante;

    private Double monto;

    private String frecuencia;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "comprobante_url")
    private String comprobanteUrl;

    private String estado;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}
