package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

import java.sql.Date;

/**
 * The type Update libro request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLibroRequest {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Fecha publicacion.
     */
    private Date fechaPublicacion;

    /**
     * The Estado.
     */
    private String estado;

    /**
     * The Paginas.
     */
    private Integer paginas;

    /**
     * The Autor.
     */
    private Long autor;

    /**
     * The Genero.
     */
    private Long genero;

    /**
     * The Tipo.
     */
    private Long tipo;

    /**
     * The Saga.
     */
    private Long saga;
}
