package org.reader.low.booknbook.controller.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NombreGrupos {

    private Long idGrupo;

    private String nombreGrupo;

    private Boolean administrador;

    private byte[] imagen;
}
