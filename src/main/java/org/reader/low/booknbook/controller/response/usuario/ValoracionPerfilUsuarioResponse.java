package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;

import java.util.List;

/**
 * The type Valoracion perfil usuario response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionPerfilUsuarioResponse {

    /**
     * The Valoraciones.
     */
    private List<ValoracionUsuario> valoraciones;
}
