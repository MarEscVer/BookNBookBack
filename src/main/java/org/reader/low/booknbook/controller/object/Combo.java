package org.reader.low.booknbook.controller.object;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * The type Combo.
 */
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Combo {

    /**
     * The Id.
     */
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * The Nombre.
     */
    @EqualsAndHashCode.Include
    private String nombre;
}
