package org.reader.low.booknbook.controller.request.libro;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibroRequest {

    private String descripcion;

    private Date fechaPublicacion;

    private String nombre;

    private Integer paginas;

    private Long idAutor;

    private Long genero;

    private Long tipo;

    private Long saga;

    private String nuevaSaga;

}
