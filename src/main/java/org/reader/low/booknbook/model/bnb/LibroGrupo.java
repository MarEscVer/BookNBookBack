package org.reader.low.booknbook.model.bnb;


import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdLibroGrupo;

@Entity
@Table(name = "libro_grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LibroGrupo {

    @EmbeddedId
    private IdLibroGrupo id;

    @Column(name = "estado", nullable=false)
    private String estado;

    @Column(name = "votos", columnDefinition = "int unsigned default 0")
    private Integer votos;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
