package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLibroRequest {

    private Long id;

    private String nombre;

    private String descripcion;

    private Date fechaPublicacion;

    private String estado;

    private Integer paginas;

    private Long autor;

    private Long genero;

    private Long tipo;

    private Long saga;
}
