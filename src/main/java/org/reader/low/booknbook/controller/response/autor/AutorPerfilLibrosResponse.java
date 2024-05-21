package org.reader.low.booknbook.controller.response.autor;

import lombok.*;
import org.reader.low.booknbook.controller.object.LibroObject;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorPerfilLibrosResponse {

    private List<LibroObject> libros;
}
