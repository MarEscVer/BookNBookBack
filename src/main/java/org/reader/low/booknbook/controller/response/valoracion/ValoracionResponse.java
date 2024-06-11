package org.reader.low.booknbook.controller.response.valoracion;

import lombok.*;

import java.sql.Date;


/**
 * The type Valoracion response.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValoracionResponse {

    /**
     * The Estado.
     */
    private String estado;

    /**
     * The Pagina actual.
     */
    private Integer paginaActual;

    /**
     * The Calificacion personal.
     */
    private Integer calificacionPersonal;

    /**
     * The Comentario.
     */
    private String comentario;

    /**
     * The Fecha comentario.
     */
    private Date fechaComentario;

    /**
     * The Fecha lectura.
     */
    private Date fechaLectura;

    /**
     * The Id libro.
     */
    private Long idLibro;
}
