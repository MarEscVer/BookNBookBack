package org.reader.low.booknbook.controller.response.libro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.LibroDescripcion;

import java.util.List;

/**
 * The type List libro response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Respuesta lista de libros",
        name = "firstName")
public class ListLibroResponse {

    /**
     * The List libros.
     */
    @Schema(description = "Lista de descripciones de libro",
            name = "listLibros")
    private List<LibroDescripcion> listLibros;
}
