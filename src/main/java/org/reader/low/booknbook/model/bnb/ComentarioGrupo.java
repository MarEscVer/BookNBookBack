package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdComentarioGrupo;

import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "comentarioGrupo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ComentarioGrupo implements Serializable {

    @EmbeddedId
    private IdComentarioGrupo id;

    @Column(name = "comentario", nullable=false, columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fechaComentarioGrupo", nullable=false)
    private Date fechaComentarioGrupo;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
