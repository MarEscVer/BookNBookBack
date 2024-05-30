package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;

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

    private String genero;

    private String tipo;

    private boolean selfPerfil;
}
