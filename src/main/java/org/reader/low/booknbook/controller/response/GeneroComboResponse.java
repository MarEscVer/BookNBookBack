package org.reader.low.booknbook.controller.response;

import lombok.*;

/**
 * The type Genero combo response.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GeneroComboResponse {

    /**
     * The Genero.
     */
    private ComboResponse genero;

    /**
     * The Tipo.
     */
    private ComboResponse tipo;
}
