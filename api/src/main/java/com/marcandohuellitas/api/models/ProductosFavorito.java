package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para ProductosFavorito.
 * Esta clase representa la tabla productos_favoritos en la base de datos.
 */
@Entity
@Table(name = "productos_favoritos")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class ProductosFavorito {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}
