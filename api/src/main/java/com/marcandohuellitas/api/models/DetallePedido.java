package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para DetallePedido.
 * Esta clase representa la tabla detalles_pedido en la base de datos.
 */
@Entity
@Table(name = "detalles_pedido")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "producto_id")
    private Long productoId;

    private Integer cantidad;

    @Column(name = "precio_compra")
    private Double precioCompra;

}
