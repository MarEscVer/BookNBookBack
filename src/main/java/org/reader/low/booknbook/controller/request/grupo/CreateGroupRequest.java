package org.reader.low.booknbook.controller.request.grupo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequest {

    private String nombreGrupo;

    private Long genero;

    private Long tipo;

    private String descripcion;
}
