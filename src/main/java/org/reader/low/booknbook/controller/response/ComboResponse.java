package org.reader.low.booknbook.controller.response;

import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

import java.util.List;

/**
 * The type Combo response.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComboResponse {

    /**
     * The Valores.
     */
    private List<Combo> valores;
}
