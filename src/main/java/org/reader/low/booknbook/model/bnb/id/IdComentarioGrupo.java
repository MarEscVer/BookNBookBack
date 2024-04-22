package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class IdComentarioGrupo implements Serializable {

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "id_grupo")
    private Long idGrupo;

    @Column(name = "id_usuario")
    private Long idUsuario;

}
