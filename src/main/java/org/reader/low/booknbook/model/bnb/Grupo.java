package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdComentarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdLibroGrupo;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Grupo.
 */
@Entity
@Table(name = "grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grupo implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Nombre.
     */
    @Column(name = "nombre", nullable=false)
    private String nombre;

    /**
     * The Descripcion.
     */
    @Lob
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /**
     * The Imagen.
     */
    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen;

    /**
     * The Estado.
     */
    @Column(name = "estado")
    private String estado;

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
     * The Usuario grupo.
     */
    @OneToMany(mappedBy = "grupo",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<UsuarioGrupo> usuarioGrupo = new ArrayList<>();

    /**
     * The Libro grupo.
     */
    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LibroGrupo> libroGrupo = new ArrayList<>();

    /**
     * The Comentario grupo.
     */
    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();


    /**
     * Add comentario grupo.
     *
     * @param libro   the libro
     * @param usuario the usuario
     */
    public void addComentarioGrupo(Libro libro, Usuario usuario){
        ComentarioGrupo comenGrupo = ComentarioGrupo.builder()
                .grupo(this)
                .usuario(usuario)
                .libro(libro)
                .id(IdComentarioGrupo.builder()
                        .idGrupo(this.getId())
                        .idLibro(libro.getId())
                        .idUsuario(usuario.getId())
                        .build())
                .build();
        this.getComentarioGrupo().add(comenGrupo);
        libro.getComentarioGrupo().add(comenGrupo);
        usuario.getComentarioGrupo().add(comenGrupo);
    }

    /**
     * Delete comentario grupo.
     *
     * @param libro      the libro
     * @param usuario    the usuario
     * @param comenGrupo the comen grupo
     */
    public void deleteComentarioGrupo(Libro libro, Usuario usuario, ComentarioGrupo comenGrupo){
        this.getComentarioGrupo().remove(comenGrupo);
        libro.getComentarioGrupo().remove(comenGrupo);
        usuario.getComentarioGrupo().remove(comenGrupo);
    }

    /**
     * Add libro grupo.
     *
     * @param libro the libro
     */
    public void addLibroGrupo(Libro libro){
        LibroGrupo libGrupo = LibroGrupo.builder()
                .grupo(this)
                .libro(libro)
                .votos(0)
                .id(IdLibroGrupo.builder()
                        .idGrupo(this.getId())
                        .idLibro(libro.getId()).build())
                .build();
        this.getLibroGrupo().add(libGrupo);
        libro.getLibroGrupo().add(libGrupo);
    }

    /**
     * Delete libro del grupo.
     *
     * @param libro      the libro
     * @param libroGrupo the libro grupo
     */
    public void deleteLibroDelGrupo(Libro libro, LibroGrupo libroGrupo){
        this.getLibroGrupo().remove(libroGrupo);
        libro.getLibroGrupo().remove(libroGrupo);
    }

    /**
     * Add usuario a grupo.
     *
     * @param usuario the usuario
     * @param rol     the rol
     */
    public void addUsuarioAGrupo(Usuario usuario, String rol){
        UsuarioGrupo usuGrupo = UsuarioGrupo.builder()
                .id(IdUsuarioGrupo.builder()
                        .idUsuario(usuario.getId())
                        .idGrupo(this.getId())
                        .build())
                .grupo(this)
                .rol(rol)
                .usuario(usuario)
                .build();
        this.getUsuarioGrupo().add(usuGrupo);
        usuario.getUsuarioGrupo().add(usuGrupo);
    }

    /**
     * Delete usuario a grupo.
     *
     * @param usuario      the usuario
     * @param usuarioGrupo the usuario grupo
     */
    public void deleteUsuarioAGrupo(Usuario usuario, UsuarioGrupo usuarioGrupo){
        this.getUsuarioGrupo().remove(usuarioGrupo);
        usuario.getUsuarioGrupo().remove(usuarioGrupo);
    }
}
