package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdSeguimiento;

import java.io.Serializable;

@Entity
@Table(name = "seguimiento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Seguimiento implements Serializable {

    @EmbeddedId
    private IdSeguimiento id;

    @Column(name = "seguidor")
    private Integer seguidor;

    @Column(name = "seguido")
    private Integer seguido;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
     
    @MapsId("idSeguidor")
    @JoinColumn(name = "id_seguidor")
    private Usuario idSeguidor;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @MapsId("idSeguido")
    @JoinColumn(name = "id_seguido")
    private Usuario idSeguido;

}
