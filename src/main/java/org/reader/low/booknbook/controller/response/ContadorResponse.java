package org.reader.low.booknbook.controller.response;

import lombok.*;

/**
 * The type Contador response.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContadorResponse {

    /**
     * The Lectores totales.
     */
    private Long lectoresTotales;

    /**
     * The Libros leidos.
     */
    private Long librosLeidos;

    /**
     * The Clubes creados.
     */
    private Long clubesCreados;

    /**
     * The Comentarios totales.
     */
    private Long comentariosTotales;

}
