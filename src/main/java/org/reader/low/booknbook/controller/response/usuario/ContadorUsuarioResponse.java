package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.ComboGenero;

/**
 * The type Contador usuario response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContadorUsuarioResponse {

    /**
     * The Paginas leidas.
     */
    private Long paginasLeidas;

    /**
     * The Libros leidos.
     */
    private Integer librosLeidos;

    /**
     * The Valoraciones.
     */
    private Integer valoraciones;

    /**
     * The Genero.
     */
    private ComboGenero genero;
}
