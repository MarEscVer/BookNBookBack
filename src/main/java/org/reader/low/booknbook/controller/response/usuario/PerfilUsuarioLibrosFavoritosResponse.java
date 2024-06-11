package org.reader.low.booknbook.controller.response.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.LibroFavorito;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type Perfil usuario libros favoritos response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioLibrosFavoritosResponse {

    /**
     * The Libros.
     */
    private List<LibroFavorito> libros;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
