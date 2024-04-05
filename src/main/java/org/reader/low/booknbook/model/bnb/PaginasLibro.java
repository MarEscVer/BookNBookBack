package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;

@Entity
@Table(name = "paginas_leidas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginasLibro {

    @EmbeddedId
    private IdPaginasLibro id;

    @Column(name = "paginasLeidas")
    private Integer paginasLeidas;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
