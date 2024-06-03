package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LecturaUsuario {

    private Long id;

    private String autor;

    private byte[] imagen;

    private String titulo;

    private String descripcion;

    private Integer paginasTotales;

    private String saga;

    private Combo genero;

    private Combo tipo;

    private Date fechaLectura;

    private Integer paginasLeidas;

}
