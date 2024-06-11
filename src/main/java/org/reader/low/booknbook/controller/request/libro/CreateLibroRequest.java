package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

import java.util.Date;

/**
 * The type Create libro request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibroRequest {

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Fecha publicacion.
     */
    private Date fechaPublicacion;

    /**
     * The Nombre.
     */
    private String nombre;

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

    /**
     * The Nueva saga.
     */
    private String nuevaSaga;

}
