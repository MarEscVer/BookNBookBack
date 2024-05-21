package org.reader.low.booknbook.model.bnb;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.reader.low.booknbook.model.bnb.id.IdLibroGrupo;

import java.io.Serializable;

@Entity
@Table(name = "libro_grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LibroGrupo implements Serializable {

    @EmbeddedId
    private IdLibroGrupo id;

    @Column(name = "estado", nullable=false)
    private String estado;

    @ColumnDefault("0")
    @Column(name = "votos")
    private Integer votos;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
