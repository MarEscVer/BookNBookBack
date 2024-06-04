package org.reader.low.booknbook.controller.response.valoracion;

import lombok.*;

import java.sql.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValoracionResponse {

    private String estado;

    private Integer paginaActual;

    private Integer calificacionPersonal;

    private String comentario;

    private Date fechaComentario;

    private Date fechaLectura;

    private Long idLibro;
}
