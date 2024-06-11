package org.reader.low.booknbook.controller.object;

import lombok.*;

/**
 * The type Group descripcion.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDescripcion {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Genero.
     */
    private ComboGenero genero;

    /**
     * The Tipo.
     */
    private ComboGenero tipo;

    /**
     * The Miembros.
     */
    private Integer miembros;

    /**
     * The Perteneces.
     */
    private Boolean perteneces;

    /**
     * The Imagen.
     */
    private byte [] imagen;
}
