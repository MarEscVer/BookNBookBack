package org.reader.low.booknbook.controller.response.autor;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorPerfilResponse {

    private String imagen;

    private String pseudonimo;

    private String biografia;

    private List<String> genero;

    private List<String> tipo;
}
