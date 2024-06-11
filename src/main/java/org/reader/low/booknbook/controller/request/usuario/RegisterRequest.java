package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

/**
 * The type Register request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * The Usuario.
     */
    private String usuario;

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
