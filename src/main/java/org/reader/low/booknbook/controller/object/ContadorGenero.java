package org.reader.low.booknbook.controller.object;

import lombok.*;

/**
 * The type Contador genero.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContadorGenero {

    /**
     * The Genero.
     */
    private ComboGenero genero;

    /**
     * The Num leidos.
     */
    private int numLeidos;
}
