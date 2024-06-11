package org.reader.low.booknbook.controller.response.denuncia;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type Moderate comments response.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModerateCommentsResponse {

    /**
     * The Estado.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "estado",
            example = "PENDIENTE")
    private String estado;

    /**
     * The Comentarios denunciados.
     */
    @Schema(description = "Lista de comentarios para moderar",
            name = "comentariosDenunciados")
    private List<ModerateComments> comentariosDenunciados;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
