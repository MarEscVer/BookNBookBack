package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuario {

    private byte[] imagenPerfil;

    private String nombre;

    private String apellidoUno;

    private String apellidoDos;

    private String username;

    private Combo genero;

    private Combo tipo;

    private boolean selfPerfil;
}
