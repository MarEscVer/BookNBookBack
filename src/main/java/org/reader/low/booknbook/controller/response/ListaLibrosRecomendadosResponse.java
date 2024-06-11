package org.reader.low.booknbook.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.LibroDescripcion;

import java.util.List;

/**
 * The type Lista libros recomendados response.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        description = "Lista de libros con filtro",
        name = "ListaLibrosRecomendadosResponse")
public class ListaLibrosRecomendadosResponse {

    /**
     * The Filter name.
     */
    @Schema(description = "Filtro usado",
            name = "filterName",
            example = "Más leidos")
    private String filterName;

    /**
     * The Libros.
     */
    @Schema(description = "Lista de libros",
            name = "libros")
    private List<LibroDescripcion> libros;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
