package org.reader.low.booknbook.controller.response.libro;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroPerfil {

    private Long id;

    private String titulo;

    private String saga;

    private Long idAutor;

    private String autor;

    private byte[] imagen;

    private Integer paginasTotales;

    private Date anyo;

    private Integer calificacionMedia;

    private String genero;

    private String tipo;

    private String descripcion;
}
