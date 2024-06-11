package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

/**
 * The type Perfil usuario.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuario {

    /**
     * The Imagen perfil.
     */
    private byte[] imagenPerfil;

    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * The Apellido uno.
     */
    private String apellidoUno;

    /**
     * The Apellido dos.
     */
    private String apellidoDos;

    /**
     * The Username.
     */
    private String username;

    /**
     * The Email.
     */
    private String email;

    /**
     * The Genero.
     */
    private Combo genero;

    /**
     * The Tipo.
     */
    private Combo tipo;

    /**
     * The Self perfil.
     */
    private boolean selfPerfil;

    /**
     * The Is follow.
     */
    private boolean isFollow;
}
