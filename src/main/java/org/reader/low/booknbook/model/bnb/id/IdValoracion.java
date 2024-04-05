package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class IdValoracion implements Serializable {

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "id_usuario")
    private Long idUsuario;

}
