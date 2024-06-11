package org.reader.low.booknbook.controller.request.autor;

import lombok.*;

/**
 * The type Create autor request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAutorRequest {

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
