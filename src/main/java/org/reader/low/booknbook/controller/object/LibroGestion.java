package org.reader.low.booknbook.controller.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

/**
 * The type Libro gestion.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroGestion {
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
            description = "Imagen del libro",
            name = "imagen",
            type = "byte")
    private byte[] imagen;

    /**
     * The Saga.
     */
    @Schema(
            description = "Titulo de la saga",
            name = "saga",
            type = "string",
            example = "Titulo saga")
    private String saga;

    /**
     * The Titulo.
     */
    @Schema(
            description = "Titulo del libro",
            name = "titulo",
            type = "string",
            example = "Titulo libro")
    private String titulo;

    /**
     * The Autor.
     */
    @Schema(
            description = "Pseudonimo del libro",
            name = "autor",
            type = "string",
            example = "Autor")
    private String autor;

    /**
     * The Genero.
     */
    @Schema(
            description = "Nombre del genero",
            name = "genero",
            type = "string",
            example = "FANTASTICA")
    private String genero;

    /**
     * The Tipo.
     */
    @Schema(
            description = "Nombre del tipo de genero",
            name = "tipo",
            type = "string",
            example = "INFANTIL")
    private String tipo;

    /**
     * The Year.
     */
    @Schema(
            description = "Fecha de publicacion del libro",
            name = "year",
            type = "Date",
            example = "2020-05-05")
    private Date year;
}
