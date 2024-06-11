package org.reader.low.booknbook.controller.object;

import lombok.*;

/**
 * The type User info.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * The Imagen usuario.
     */
    private byte[] imagenUsuario;

    /**
     * The Username.
     */
    private String username;

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Apellido 1.
     */
    private String apellido1;

    /**
     * The Apellido 2.
     */
    private String apellido2;

    /**
     * The Email.
     */
    private String email;

    /**
     * The Rol.
     */
    private String rol;
}
