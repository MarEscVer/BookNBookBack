package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Lob
    @Column(name = "imagen", columnDefinition="LONGBLOB")
    private byte[] imagen;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade=CascadeType.ALL)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL)
    private List<UsuarioGrupo> usuarioGrupo;

    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL)
    private List<LibroGrupo> libroGrupo;

    @OneToMany(mappedBy = "grupo", cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo;
}
