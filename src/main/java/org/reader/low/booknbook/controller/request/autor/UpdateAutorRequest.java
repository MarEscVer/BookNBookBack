package org.reader.low.booknbook.controller.request.autor;

import lombok.*;

/**
 * The type Update autor request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAutorRequest {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Pseudonimo.
     */
    private String pseudonimo;

    /**
     * The Localidad.
     */
    private String localidad;

    /**
     * The Biografia.
     */
    private String biografia;
}
