package org.reader.low.booknbook.controller.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * The type Libro descripcion.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDescripcion {

    /**
     * The Id.
     */
    @Schema(
            description = "Identificador del libro",
            name = "id",
            type = "Long",
            example = "1")
    private Long id;

    /**
     * The Imagen.
     */
    @Schema(
            description = "Portada del libro",
            name = "imagen",
            type = "byte")
    private byte[] imagen;

    /**
     * The Saga.
     */
    @Schema(
            description = "Nombre de la saga",
            name = "saga",
            type = "string",
            example = "Saga")
    private String saga;

    /**
     * The Titulo.
     */
    @Schema(
            description = "Titulo del libro",
            name = "titulo",
            type = "string",
            example = "Titulo")
    private String titulo;

    /**
     * The Autor.
     */
    @Schema(
            description = "Autor del libro",
            name = "autor",
            type = "string",
            example = "Joana Marcús")
    private String autor;
}
