package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroGestion {

    private Long id;

    private byte[] imagen;

    private String saga;

    private String titulo;

    private String autor;

    private String genero;

    private String tipo;

    private Date year;
}
