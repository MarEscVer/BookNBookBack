package org.reader.low.booknbook.controller.response.libro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.LibroGestion;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type List libro gestion response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListLibroGestionResponse {
    /**
     * The Libros.
     */
    @Schema(description = "Lista de libros para gestionar",
            name = "libros")
    private List<LibroGestion> libros;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
