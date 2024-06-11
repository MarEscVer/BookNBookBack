package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The type Id valoracion.
 */
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdValoracion implements Serializable {

    /**
     * The Id libro.
     */
    @Column(name = "id_libro")
    private Long idLibro;

    /**
     * The Id usuario.
     */
    @Column(name = "id_usuario")
    private Long idUsuario;

}
