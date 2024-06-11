package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The type Id seguimiento.
 */
@Embeddable
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class IdSeguimiento implements Serializable {

    /**
     * The Id seguido.
     */
    @Column(name = "id_seguido")
    private Long idSeguido;

    /**
     * The Id seguidor.
     */
    @Column(name = "id_seguidor")
    private Long idSeguidor;
}
