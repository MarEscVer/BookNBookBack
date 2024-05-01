package org.reader.low.booknbook.controller.object;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Combo {

    private Long id;

    private String nombre;
}
