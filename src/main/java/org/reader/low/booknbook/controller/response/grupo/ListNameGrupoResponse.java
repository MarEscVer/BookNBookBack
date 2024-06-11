package org.reader.low.booknbook.controller.response.grupo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.NombreGrupos;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type List name grupo response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListNameGrupoResponse {

    /**
     * The Nombre grupos.
     */
    List<NombreGrupos> nombreGrupos;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;

}
