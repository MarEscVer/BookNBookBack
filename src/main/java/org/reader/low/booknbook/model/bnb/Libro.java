package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libro")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Lob
    @Column(name = "descripcion", nullable=false)
    private String descripcion;

    @Column(name = "fechaPublicacion", nullable=false)
    private Date fechaPublicacion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pagTotal", nullable=false)
    private Integer pagTotal;

    @Lob
    @Column(name = "fotoLibro", columnDefinition = "LONGBLOB")
    private byte[] fotoLibro;

    @ManyToOne(fetch = FetchType.EAGER, optional = false,  cascade=CascadeType.ALL)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_saga")
    private Saga saga;

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<LibroGrupo> libroGrupo = new ArrayList<>();

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<PaginasLibro> paginasLibro = new ArrayList<>();

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

}
