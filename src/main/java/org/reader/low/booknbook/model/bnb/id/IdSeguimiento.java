package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.*;
import org.reader.low.booknbook.model.bnb.Usuario;

import java.io.Serializable;

@Embeddable
public class IdSeguimiento implements Serializable {

    @Column(name = "id_seguido")
    private Long idSeguido;

    @Column(name = "id_seguidor")
    private Long idSeguidor;
}
