package org.reader.low.booknbook.controller.response.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.reader.low.booknbook.controller.object.LecturaUsuario;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type Libros propios usuario response.
 */
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibrosPropiosUsuarioResponse {

    /**
     * The Libros.
     */
    private List<LecturaUsuario> libros;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
