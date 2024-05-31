package org.reader.low.booknbook.controller.request.denuncia;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaRequest {

    private Long idLibro;

    private Long idUsuario;

    private String motivo;

    private String texto;

    private boolean grupo;

}
