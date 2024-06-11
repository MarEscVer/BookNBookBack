package org.reader.low.booknbook.controller.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * The type Libro object.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Schema(
        description = "Objeto libro",
        name = "LibroObject")
public class LibroObject {

    /**
     * The Id.
     */
    @Schema(
            description = "Id del libro",
            name = "id",
            example = "1")
    private Long id;

    /**
     * The Nombre.
     */
    @Schema(
            description = "Titulo del libro",
            name = "nombre",
            example = "Titulo del libro")
    private String nombre;

    /**
     * The Descripcion.
     */
    @Schema(
            description = "Descripcion del libro",
            name = "descripcion",
            example = "Descripcion")
    private String descripcion;

    /**
     * The Fecha publicacion.
     */
    @Schema(
            description = "Fecha de publicacion del libro",
            name = "fechaPublicacion",
            example = "05-05-2024")
    private Date fechaPublicacion;

    /**
     * The Foto libro.
     */
    @Schema(
            description = "Portada del libro",
            name = "fotoLibro")
    private byte[] fotoLibro;

    /**
     * The Saga.
     */
    @Schema(
            description = "Saga a la que pertenece el libro",
            name = "saga",
            example = "Saga")
    private String saga;

    /**
     * The Grupos.
     */
    @Schema(
            description = "Cantidad de grupos a los que está agregado el libro",
            name = "grupos",
            example = "35")
    private Integer grupos;

    /**
     * The Paginas libro.
     */
    @Schema(
            description = "Paginas que tiene el libro",
            name = "paginasLibro",
            example = "350")
    private Integer paginasLibro;

    /**
     * The Valoracion.
     */
    @Schema(
            description = "Puntuacion que tiene el libro",
            name = "valoracion",
            example = "3")
    private BigDecimal valoracion;

    /**
     * The Comentarios.
     */
    @Schema(
            description = "Numero de comentarios que han dejado sobre el libro",
            name = "comentarios",
            example = "600")
    private Integer comentarios;
}
