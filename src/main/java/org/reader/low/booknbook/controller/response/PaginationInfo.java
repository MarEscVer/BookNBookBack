package org.reader.low.booknbook.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Pagination info.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        description = "Informacion para paginar",
        name = "PaginationInfo")
public class PaginationInfo {

    /**
     * The Elements per page.
     */
    @Schema(description = "Elementos que hay en cada página",
            name = "elementsPerPage",
            example = "5")
    private Integer elementsPerPage;

    /**
     * The Total pages.
     */
    @Schema(description = "Total de páginas con información",
            name = "totalPages",
            example = "5")
    private Integer totalPages;

    /**
     * The First page.
     */
    @Schema(description = "Primera página",
            name = "firstPage",
            example = "1")
    private Integer firstPage;

    /**
     * The Last page.
     */
    @Schema(description = "Última Pagina",
            name = "lastPage",
            example = "5")
    private Integer lastPage;

    /**
     * The Total elements.
     */
    @Schema(description = "Elementos totales que hay para mostrar",
            name = "totalElements",
            example = "25")
    private Integer totalElements;

    /**
     * The Actual page.
     */
    @Schema(description = "Página actual",
            name = "actualPage",
            example = "3")
    private Integer actualPage;
}
