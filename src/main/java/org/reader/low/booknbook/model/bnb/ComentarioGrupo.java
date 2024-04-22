package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdComentarioGrupo;

import java.sql.Date;


@Entity
@Table(name = "comentarioGrupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ComentarioGrupo {

    @EmbeddedId
    private IdComentarioGrupo id;

    @Column(name = "comentario", nullable=false, columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fechaComentarioGrupo", nullable=false)
    private Date fechaComentarioGrupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
