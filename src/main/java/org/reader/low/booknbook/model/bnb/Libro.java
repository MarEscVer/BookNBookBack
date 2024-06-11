package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Libro.
 */
@Entity
@Table(name = "libro")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Nombre.
     */
    @Column(name = "nombre", nullable=false)
    private String nombre;

    /**
     * The Descripcion.
     */
    @Column(name = "descripcion", nullable=false, columnDefinition = "TEXT")
    private String descripcion;

    /**
     * The Fecha publicacion.
     */
    @Column(name = "fechaPublicacion", nullable=false)
    private Date fechaPublicacion;

    /**
     * The Estado.
     */
    @Column(name = "estado")
    private String estado;

    /**
     * The Pag total.
     */
    @Column(name = "pagTotal", nullable=false)
    private Integer pagTotal;

    /**
     * The Foto libro.
     */
    @Lob
    @Column(name = "fotoLibro", columnDefinition = "LONGBLOB")
    private byte[] fotoLibro;

    /**
     * The Autor.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false,  cascade=CascadeType.ALL)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    /**
     * The Genero.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    /**
     * The Tipo.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    /**
     * The Saga.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_saga")
    private Saga saga;

    /**
     * The Libro grupo.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<LibroGrupo> libroGrupo = new ArrayList<>();

    /**
     * The Paginas libro.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<PaginasLibro> paginasLibro = new ArrayList<>();

    /**
     * The Valoracion.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

    /**
     * The Comentario grupo.
     */
    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

}
