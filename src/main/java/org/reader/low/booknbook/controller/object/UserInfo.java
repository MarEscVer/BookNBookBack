package org.reader.low.booknbook.controller.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private byte[] imagenUsuario;

    private String username;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private String rol;
}
