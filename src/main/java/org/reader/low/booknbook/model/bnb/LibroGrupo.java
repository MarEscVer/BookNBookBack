package org.reader.low.booknbook.model.bnb;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.reader.low.booknbook.model.bnb.id.IdLibroGrupo;

import java.io.Serializable;

/**
 * The type Libro grupo.
 */
@Entity
@Table(name = "libro_grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LibroGrupo implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private IdLibroGrupo id;

    /**
     * The Estado.
     */
    @Column(name = "estado", nullable=false)
    private String estado;

    /**
     * The Votos.
     */
    @ColumnDefault("0")
    @Column(name = "votos")
    private Integer votos;

    /**
     * The Libro.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    /**
     * The Grupo.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
