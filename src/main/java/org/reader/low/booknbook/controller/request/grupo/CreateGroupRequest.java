package org.reader.low.booknbook.controller.request.grupo;

import lombok.*;

/**
 * The type Create group request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequest {

    /**
     * The Nombre grupo.
     */
    private String nombreGrupo;

    /**
     * The Genero.
     */
    private Long genero;

    /**
     * The Tipo.
     */
    private Long tipo;

    /**
     * The Descripcion.
     */
    private String descripcion;
}
