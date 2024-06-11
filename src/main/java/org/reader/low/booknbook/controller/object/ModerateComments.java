package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

/**
 * The type Moderate comments.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ModerateComments {

    /**
     * The Id denuncia.
     */
    private Long idDenuncia;

    /**
     * The Id valoracion libro.
     */
    private Long idValoracionLibro;

    /**
     * The Id valoracion usuario.
     */
    private Long idValoracionUsuario;

    /**
     * The Nombre usuario.
     */
    private String nombreUsuario;

    /**
     * The Fecha denuncia.
     */
    private Date fechaDenuncia;

    /**
     * The Comentario.
     */
    private String comentario;

    /**
     * The Motivo.
     */
    private String motivo;
}
