package org.reader.low.booknbook.controller.object;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ComboGenero extends Combo{

    private String color;
}
