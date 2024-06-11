package org.reader.low.booknbook.controller.response.autor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * The type Autor perfil response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Respuesta Perfil del Autor",
        name = "firstName")
public class AutorPerfilResponse {

    /**
     * The Id.
     */
    @Schema(description = "Identificador del Autor",
            name = "id",
            example = "1")
    private Long id;

    /**
     * The Imagen.
     */
    @Schema(description = "Imagen del autor",
            name = "imagen")
    private byte[] imagen;

    /**
     * The Pseudonimo.
     */
    @Schema(description = "Pseudonimo con el que se reconoce al autor",
            name = "pseudonimo",
            example = "Joana Marcús")
    private String pseudonimo;

    /**
     * The Biografia.
     */
    @Schema(description = "Biografia del autor",
            name = "biografia",
            example = "biografia")
    private String biografia;

    /**
     * The Localidad.
     */
    @Schema(description = "Procedencia del autor",
            name = "localidad",
            example = "España")
    private String localidad;
}
