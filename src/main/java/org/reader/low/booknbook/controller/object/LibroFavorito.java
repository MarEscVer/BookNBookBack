package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.sql.Date;

/**
 * The type Libro favorito.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroFavorito {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Fecha publicacion.
     */
    private Date fechaPublicacion;

    /**
     * The Foto libro.
     */
    private byte[] fotoLibro;

    /**
     * The Saga.
     */
    private String saga;

    /**
     * The Paginas libro.
     */
    private Integer paginasLibro;

    /**
     * The Valoracion.
     */
    private Integer valoracion;


}
