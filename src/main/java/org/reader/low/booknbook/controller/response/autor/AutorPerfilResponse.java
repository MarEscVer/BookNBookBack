package org.reader.low.booknbook.controller.response.autor;

import lombok.*;
import org.reader.low.booknbook.controller.object.LibroObject;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorPerfilResponse {

    private byte[] imagen;

    private String pseudonimo;

    private String biografia;

    private List<LibroObject> libros;
}
