package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The type Id comentario grupo.
 */
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdComentarioGrupo implements Serializable {

    /**
     * The Id libro.
     */
    @Column(name = "id_libro")
    private Long idLibro;

    /**
     * The Id grupo.
     */
    @Column(name = "id_grupo")
    private Long idGrupo;

    /**
     * The Id usuario.
     */
    @Column(name = "id_usuario")
    private Long idUsuario;

}
