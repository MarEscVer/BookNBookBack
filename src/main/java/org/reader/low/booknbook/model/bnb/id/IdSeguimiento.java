package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class IdSeguimiento implements Serializable {

    @Column(name = "id_seguido")
    private Long idSeguido;

    @Column(name = "id_seguidor")
    private Long idSeguidor;
}
