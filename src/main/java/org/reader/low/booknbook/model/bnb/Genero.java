package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "genero",fetch = FetchType.EAGER)
    private List<Libro> libroGenero = new ArrayList<>();

    @OneToMany(mappedBy = "genero",fetch = FetchType.EAGER)
    private List<Libro> libroTipo = new ArrayList<>();

    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Grupo> grupoGenero = new ArrayList<>();

    @OneToMany(mappedBy = "genero", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Grupo> grupoTipo = new ArrayList<>();

    @ManyToMany(mappedBy = "preferenciaUsuario", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Usuario> preferenciaUsuario = new ArrayList<>();

    public void addPreferenciaUsuario(Usuario usuario) {
        if(preferenciaUsuario == null){
            preferenciaUsuario = new ArrayList<>();
        }
        if(usuario != null){
            preferenciaUsuario.add(usuario);
        }
    }
}
