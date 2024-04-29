package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuntuarLibroRequest {

    private Long idLibro;

    private Integer puntuacion;

    private String comentario;
}
