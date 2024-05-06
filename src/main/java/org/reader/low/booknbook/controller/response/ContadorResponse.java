package org.reader.low.booknbook.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContadorResponse {

    private Long lectoresTotales;

    private Long librosLeidos;

    private Long clubesCreados;

    private Long comentariosTotales;

}
