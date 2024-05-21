package org.reader.low.booknbook.controller.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroGestion {

    private byte[] imagen;

    private String saga;

    private String titulo;

    private String autor;

    private String genero;

    private String tipo;

    private Integer year;
}
