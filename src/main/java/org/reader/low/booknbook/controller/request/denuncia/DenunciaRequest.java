package org.reader.low.booknbook.controller.request.denuncia;

import lombok.*;

/**
 * The type Denuncia request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaRequest {

    /**
     * The Id libro.
     */
    private Long idLibro;

    /**
     * The Id usuario.
     */
    private Long idUsuario;

    /**
     * The Motivo.
     */
    private String motivo;

    /**
     * The Texto.
     */
    private String texto;

    /**
     * The Grupo.
     */
    private boolean grupo;

}
