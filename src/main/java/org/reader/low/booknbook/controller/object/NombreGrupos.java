package org.reader.low.booknbook.controller.object;

import lombok.*;

/**
 * The type Nombre grupos.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NombreGrupos {

    /**
     * The Id grupo.
     */
    private Long idGrupo;

    /**
     * The Nombre grupo.
     */
    private String nombreGrupo;

    /**
     * The Administrador.
     */
    private Boolean administrador;

    /**
     * The Imagen.
     */
    private byte[] imagen;
}
