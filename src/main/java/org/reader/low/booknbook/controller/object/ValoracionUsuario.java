package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionUsuario {

    private String username;

    private Date fechaComentario;

    private String comentario;

    private Long valoracionIdLibro;

    private Long valoracionIdUsuario;

    private Integer valoracion;

    private byte[] imagenUsuario;

    private boolean estaDenunciado;

}
