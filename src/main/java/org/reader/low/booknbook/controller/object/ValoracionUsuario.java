package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

/**
 * The type Valoracion usuario.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionUsuario {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Fecha comentario.
     */
    private Date fechaComentario;

    /**
     * The Comentario.
     */
    private String comentario;

    /**
     * The Valoracion id libro.
     */
    private Long valoracionIdLibro;

    /**
     * The Valoracion id usuario.
     */
    private Long valoracionIdUsuario;

    /**
     * The Valoracion.
     */
    private Integer valoracion;

    /**
     * The Imagen usuario.
     */
    private byte[] imagenUsuario;

    /**
     * The Esta denunciado.
     */
    private boolean estaDenunciado;

}
