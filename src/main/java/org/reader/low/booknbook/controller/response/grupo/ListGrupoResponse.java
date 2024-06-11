package org.reader.low.booknbook.controller.response.grupo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.GroupDescripcion;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type List grupo response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListGrupoResponse {

    /**
     * The List group.
     */
    @Schema(description = "Lista de descripciones de grupo",
            name = "listGroup")
    private List<GroupDescripcion> listGroup;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
