package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libro")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT", nullable=false)
    private String descripcion;

    @Column(name = "fechaPublicacion", nullable=false)
    private Date fechaPublicacion;

    @Column(name = "pagTotal", nullable=false)
    private Integer pagTotal;

    @Lob
    @Column(name = "fotoLibro", columnDefinition="LONGBLOB")
    private byte[] fotoLibro;

    @ManyToOne(optional = false, /*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne(optional = false, /*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(optional = false, /*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_saga")
    private Saga saga;

    @OneToMany(mappedBy = "libro", cascade=CascadeType.ALL)
    private Set<LibroGrupo> libroGrupo;

    @OneToMany(mappedBy = "libro", cascade=CascadeType.ALL)
    private Set<PaginasLibro> paginasLibro;

    @OneToMany(mappedBy = "libro", cascade=CascadeType.ALL)
    private Set<Valoracion> valoracion;

    @OneToMany(mappedBy = "libro", cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo;

}
