package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
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
public class Libro {

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

    @Column(name = "fotoLibro")
    private Blob fotoLibro;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_saga")
    private Saga saga;

    @OneToMany(mappedBy = "libro")
    private Set<LibroGrupo> libroGrupo;

    @OneToMany(mappedBy = "libro")
    private Set<PaginasLibro> paginasLibro;

    @OneToMany(mappedBy = "libro")
    private Set<Valoracion> valoracion;

    @OneToMany(mappedBy = "libro")
    private List<ComentarioGrupo> comentarioGrupo;

}
