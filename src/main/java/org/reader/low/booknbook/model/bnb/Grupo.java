package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private Genero tipo;

    @OneToMany(mappedBy = "grupo")
    private List<UsuarioGrupo> usuarioGrupo;

    @OneToMany(mappedBy = "grupo")
    private List<LibroGrupo> libroGrupo;

    @OneToMany(mappedBy = "grupo")
    private List<ComentarioGrupo> comentarioGrupo;
}
