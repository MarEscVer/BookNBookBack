package org.reader.low.booknbook.controller.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDescripcion {

    private Long id;

    private byte[] imagen;

    private String saga;

    private String titulo;

    private String autor;
}
