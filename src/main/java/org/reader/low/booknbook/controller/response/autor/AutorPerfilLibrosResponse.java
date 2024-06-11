package org.reader.low.booknbook.controller.response.autor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.LibroObject;

import java.util.List;

/**
 * The type Autor perfil libros response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Respuesta Autor perfil libro",
        name = "AutorPerfilLibrosResponse")
public class AutorPerfilLibrosResponse {

    /**
     * The Libros.
     */
    @Schema(description = "Lista de objeto libro",
            name = "libros")
    private List<LibroObject> libros;
}
