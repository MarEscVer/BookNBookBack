package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

/**
 * The type Puntuar libro request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuntuarLibroRequest {

    /**
     * The Id libro.
     */
    private Long idLibro;

    /**
     * The Puntuacion.
     */
    private Integer puntuacion;

    /**
     * The Comentario.
     */
    private String comentario;
}
