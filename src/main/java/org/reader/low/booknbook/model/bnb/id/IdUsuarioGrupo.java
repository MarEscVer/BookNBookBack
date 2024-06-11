package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The type Id usuario grupo.
 */
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdUsuarioGrupo implements Serializable {

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
