package org.reader.low.booknbook.controller.response.libro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Libro perfil.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroPerfil {

    /**
     * The Id.
     */
    @Schema(description = "Identificador de Libro",
            name = "id",
            example = "1")
    private Long id;

    /**
     * The Titulo.
     */
    @Schema(description = "Titulo del libro",
            name = "titulo",
            example = "Vatsal")
    private String titulo;

    /**
     * The Id saga.
     */
    @Schema(description = "Identificador de Saga",
            name = "idSaga",
            example = "1")
    private Long idSaga;

    /**
     * The Saga.
     */
    @Schema(description = "Titulo de saga",
            name = "saga",
            example = "Titulo saga")
    private String saga;

    /**
     * The Id autor.
     */
    @Schema(description = "Identificador de Autor",
            name = "idAutor",
            example = "1")
    private Long idAutor;

    /**
     * The Autor.
     */
    @Schema(description = "Pseudonimo del autor",
            name = "autor",
            example = "Autor")
    private String autor;

    /**
     * The Imagen.
     */
    @Schema(description = "Imagen del autor",
            name = "imagen")
    private byte[] imagen;

    /**
     * The Paginas totales.
     */
    @Schema(description = "Paginas que tiene el libro",
            name = "paginasTotales",
            example = "350")
    private Integer paginasTotales;

    /**
     * The Anyo.
     */
    @Schema(description = "Fecha de publicacion del libro",
            name = "anyo",
            example = "2020-05-14")
    private Date anyo;

    /**
     * The Calificacion media.
     */
    @Schema(description = "Calificacion media de todos los usuarios",
            name = "calificacionMedia",
            example = "3")
    private BigDecimal calificacionMedia;

    /**
     * The Genero.
     */
    @Schema(description = "Genero del libro",
            name = "genero")
    private Combo genero;

    /**
     * The Tipo.
     */
    @Schema(description = "Tipo del libro",
            name = "tipo")
    private Combo tipo;

    /**
     * The Descripcion.
     */
    @Schema(description = "Descripcion del libro",
            name = "descripcion",
            example = "Descripcion larga del libro")
    private String descripcion;

    /**
     * The Contador comentario.
     */
    @Schema(description = "Cantidad de comentarios que tiene el libro",
            name = "contadorComentario",
            example = "15")
    private Integer contadorComentario;

    /**
     * The Estado.
     */
    @Schema(description = "Estado del libro",
            name = "estado",
            example = "LEIDO")
    private String estado;
}
