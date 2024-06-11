package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Genero.
 */
@Entity
@Table(name = "genero")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Genero implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    Long id;

    /**
     * The Nombre.
     */
    @Column(name = "nombre", nullable=false)
    String nombre;

    /**
     * The Tipo.
     */
    @Column(name = "tipo", nullable=false)
    String tipo;

    /**
     * The Libro genero.
     */
    @OneToMany(mappedBy = "genero",fetch = FetchType.EAGER)
    List<Libro> libroGenero = new ArrayList<>();

    /**
     * The Libro tipo.
     */
    @OneToMany(mappedBy = "genero",fetch = FetchType.EAGER)
    List<Libro> libroTipo = new ArrayList<>();

    /**
     * The Grupo genero.
     */
    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    List<Grupo> grupoGenero = new ArrayList<>();

    /**
     * The Grupo tipo.
     */
    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    List<Grupo> grupoTipo = new ArrayList<>();

    /**
     * The Preferencia usuario.
     */
    @ManyToMany(mappedBy = "preferenciaUsuario", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    List<Usuario> preferenciaUsuario = new ArrayList<>();

    /**
     * Add preferencia usuario.
     *
     * @param usuario the usuario
     */
    public void addPreferenciaUsuario(Usuario usuario) {
        if(preferenciaUsuario == null){
            preferenciaUsuario = new ArrayList<>();
        }
        if(usuario != null){
            preferenciaUsuario.add(usuario);
        }
    }
}
