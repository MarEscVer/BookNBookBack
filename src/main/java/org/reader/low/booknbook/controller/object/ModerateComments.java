package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ModerateComments {

    private Long idDenuncia;

    private Long idValoracionLibro;

    private Long idValoracionUsuario;

    private String nombreUsuario;

    private Date fechaDenuncia;

    private String comentario;

    private String motivo;
}
