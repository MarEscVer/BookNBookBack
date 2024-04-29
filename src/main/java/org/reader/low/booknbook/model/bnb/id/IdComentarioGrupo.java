package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdComentarioGrupo implements Serializable {

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "id_grupo")
    private Long idGrupo;

    @Column(name = "id_usuario")
    private Long idUsuario;

}
