package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdComentarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdLibroGrupo;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Lob
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @OneToMany(mappedBy = "grupo",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<UsuarioGrupo> usuarioGrupo = new ArrayList<>();

    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LibroGrupo> libroGrupo = new ArrayList<>();

    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();



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

    public void deleteComentarioGrupo(Libro libro, Usuario usuario, ComentarioGrupo comenGrupo){
        this.getComentarioGrupo().remove(comenGrupo);
        libro.getComentarioGrupo().remove(comenGrupo);
        usuario.getComentarioGrupo().remove(comenGrupo);
    }

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

    public void deleteLibroDelGrupo(Libro libro, LibroGrupo libroGrupo){
        this.getLibroGrupo().remove(libroGrupo);
        libro.getLibroGrupo().remove(libroGrupo);
    }

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

    public void deleteUsuarioAGrupo(Usuario usuario, UsuarioGrupo usuarioGrupo){
        this.getUsuarioGrupo().remove(usuarioGrupo);
        usuario.getUsuarioGrupo().remove(usuarioGrupo);
    }
}
