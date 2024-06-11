package org.reader.low.booknbook.controller.object;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * The type Combo genero.
 */
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ComboGenero extends Combo{

    /**
     * The Color.
     */
    private String color;
}
