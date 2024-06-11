package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;

import java.io.Serializable;

/**
 * The type Paginas libro.
 */
@Entity
@Table(name = "paginas_leidas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginasLibro implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private IdPaginasLibro id;

    /**
     * The Paginas leidas.
     */
    @Column(name = "paginasLeidas")
    private Integer paginasLeidas;

    /**
     * The Libro.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    /**
     * The Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
