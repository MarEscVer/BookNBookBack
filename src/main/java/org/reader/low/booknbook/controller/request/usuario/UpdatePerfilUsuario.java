package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

/**
 * The type Update perfil usuario.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePerfilUsuario {

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Apellido primero.
     */
    private String apellidoPrimero;

    /**
     * The Apellido segundo.
     */
    private String apellidoSegundo;

    /**
     * The Id genero.
     */
    private Long idGenero;

    /**
     * The Id tipo.
     */
    private Long idTipo;

    /**
     * The Email.
     */
    private String email;

    /**
     * The Password.
     */
    private String password;

}
