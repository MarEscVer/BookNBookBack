package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

/**
 * The type Lectura usuario.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LecturaUsuario {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Autor.
     */
    private String autor;

    /**
     * The Imagen.
     */
    private byte[] imagen;

    /**
     * The Titulo.
     */
    private String titulo;

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Paginas totales.
     */
    private Integer paginasTotales;

    /**
     * The Saga.
     */
    private String saga;

    /**
     * The Genero.
     */
    private Combo genero;

    /**
     * The Tipo.
     */
    private Combo tipo;

    /**
     * The Fecha lectura.
     */
    private Date fechaLectura;

    /**
     * The Paginas leidas.
     */
    private Integer paginasLeidas;

}
