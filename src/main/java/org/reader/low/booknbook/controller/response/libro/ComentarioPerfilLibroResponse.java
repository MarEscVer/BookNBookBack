package org.reader.low.booknbook.controller.response.libro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type Comentario perfil libro response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioPerfilLibroResponse {

    /**
     * The Valoraciones.
     */
    @Schema(description = "Lista valoraciones de usuario",
            name = "valoraciones")
    private List<ValoracionUsuario> valoraciones;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
