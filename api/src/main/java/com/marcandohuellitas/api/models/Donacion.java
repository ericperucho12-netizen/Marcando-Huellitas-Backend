package com.marcandohuellitas.api.models;
//Primero realizar importaciones y conexiones.
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="donaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Porque un usuario puede realizar muchas donaciones
    @JoinColumn(name = "usuario_id")  // Indica que columna tiene relación.
    private Usuario usuario;

    @Column(name = "nombre_donante", length = 150)
    private String nombreDonante;

    @Column(name ="correo_donante", length =150)
    private String correoDonante;

    @Column(name = "telefono_donante", length = 20)
    private String telefonoDonante;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;   //Para dinero conviene usar BigDecimal

    @Column(length = 50)
    private String frecuencia = "Única";

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "comprobante_url", length = 255)
    private String comprobanteUrl;

    @Column(length = 50)
    private String estado = "COMPLETADA";

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;
}
