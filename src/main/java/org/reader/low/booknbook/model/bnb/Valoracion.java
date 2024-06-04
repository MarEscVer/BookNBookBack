package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "valoracion")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Valoracion implements Serializable {

    @EmbeddedId
    private IdValoracion id;

    @Column(name = "estado", nullable=false)
    private String estado;

    @Column(name = "paginaActual")
    private Integer paginaActual;

    @Column(name = "calificacionPersonal")
    private Integer calificacionPersonal;

    @Lob
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fechaComentario")
    private Date fechaComentario;

    @Column(name = "fechaLectura")
    private Date fechaLectura;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;

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
