package org.reader.low.booknbook.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GeneroComboResponse {

    private ComboResponse genero;

    private ComboResponse tipo;
}
