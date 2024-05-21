package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String usuario;

    private String nombre;

    private String apellidoPrimero;

    private String apellidoSegundo;

    private Long idGenero;

    private Long idTipo;

    private String email;

    private String password;

}
