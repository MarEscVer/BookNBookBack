package org.reader.low.booknbook.controller.response;

import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComboResponse {

    private List<Combo> valores;
}
