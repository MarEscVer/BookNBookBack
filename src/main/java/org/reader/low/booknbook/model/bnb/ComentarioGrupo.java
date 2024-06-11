package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdComentarioGrupo;

import java.io.Serializable;
import java.sql.Date;


/**
 * The type Comentario grupo.
 */
@Entity
@Table(name = "comentarioGrupo")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ComentarioGrupo implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private IdComentarioGrupo id;

    /**
     * The Comentario.
     */
    @Lob
    @Column(name = "comentario", nullable=false, columnDefinition = "TEXT")
    private String comentario;

    /**
     * The Fecha comentario grupo.
     */
    @Column(name = "fechaComentarioGrupo", nullable=false)
    private Date fechaComentarioGrupo;

    /**
     * The Denuncia.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;

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

    /**
     * The Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
