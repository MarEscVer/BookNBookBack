package org.reader.low.booknbook.controller.response.autor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorPerfilResponse {

    private Long id;

    private byte[] imagen;

    private String pseudonimo;

    private String biografia;

    private String localidad;
}
