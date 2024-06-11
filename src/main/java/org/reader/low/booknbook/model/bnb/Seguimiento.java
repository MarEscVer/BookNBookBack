package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdSeguimiento;

import java.io.Serializable;

/**
 * The type Seguimiento.
 */
@Entity
@Table(name = "seguimiento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seguimiento implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private IdSeguimiento id;

    /**
     * The Seguidor.
     */
    @Column(name = "seguidor")
    private boolean seguidor;

    /**
     * The Seguido.
     */
    @Column(name = "seguido")
    private boolean seguido;

    /**
     * The Id seguidor.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idSeguidor")
    @JoinColumn(name = "id_seguidor")
    private Usuario idSeguidor;

    /**
     * The Id seguido.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idSeguido")
    @JoinColumn(name = "id_seguido")
    private Usuario idSeguido;

}
