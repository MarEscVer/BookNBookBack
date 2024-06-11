package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

/**
 * The type Valoracion.
 */
@Entity
@Table(name = "valoracion")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Valoracion implements Serializable {

    /**
     * The Id.
     */
    @EmbeddedId
    private IdValoracion id;

    /**
     * The Estado.
     */
    @Column(name = "estado", nullable=false)
    private String estado;

    /**
     * The Pagina actual.
     */
    @Column(name = "paginaActual")
    private Integer paginaActual;

    /**
     * The Calificacion personal.
     */
    @Column(name = "calificacionPersonal")
    private Integer calificacionPersonal;

    /**
     * The Comentario.
     */
    @Lob
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    /**
     * The Fecha comentario.
     */
    @Column(name = "fechaComentario")
    private Date fechaComentario;

    /**
     * The Fecha lectura.
     */
    @Column(name = "fechaLectura")
    private Date fechaLectura;

    /**
     * The Denuncia.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;

    /**
     * The Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /**
     * The Libro.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

    /**
     * Add pagina libro paginas libro.
     *
     * @param paginaActual the pagina actual
     * @return the paginas libro
     */
    public PaginasLibro addPaginaLibro(Integer paginaActual){
        PaginasLibro pagLib = PaginasLibro.builder()
                .id(IdPaginasLibro.builder()
                        .idLibro(libro.getId())
                        .idUsuario(usuario.getId())
                        .fecha(new Date(Instant.now().toEpochMilli()))
                        .build())
                .paginasLeidas(paginaActual - (this.paginaActual != null ? paginaActual : 0))
                .usuario(usuario)
                .libro(libro)
                .build();
        libro.getPaginasLibro().add(pagLib);
        usuario.getPaginasLibro().add(pagLib);
        return pagLib;
    }
}
