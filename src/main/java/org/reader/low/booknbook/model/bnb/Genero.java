package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genero")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "tipo", nullable=false)
    private String tipo;

    @OneToMany(mappedBy = "genero")
    private List<Libro> libroGenero;

    @OneToMany(mappedBy = "genero")
    private List<Libro> libroTipo;

    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL)
    private List<Grupo> grupoGenero;

    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL)
    private List<Grupo> grupoTipo;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "preferencia_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_genero"))
    Set<Usuario> preferenciaUsuario;
}
