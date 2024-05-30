package org.reader.low.booknbook.controller.request.autor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAutorRequest {

    private Long id;

    private String pseudonimo;

    private String localidad;

    private String biografia;
}
