package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreUsuario", nullable=false)
    private String nombreUsuario;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "apellido1", nullable=false)
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "correo", nullable=false)
    private String correo;

    @ColumnDefault(value = "'NORMAL'")
    @Column(name = "rol", nullable=false)
    private String rol;

    @Column(name = "password", nullable=false)
    @Size(max = 300)
    private String password;

    @Lob
    @Column(name = "fotoPerfil", columnDefinition = "LONGBLOB")
    private byte[] fotoPerfil;

    @Column(name = "estado", nullable=false, columnDefinition = "BOOLEAN default true")
    private boolean estado;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<PaginasLibro> paginasLibro = new ArrayList<>();

    @OneToMany(mappedBy = "idSeguido", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Seguimiento> seguido = new ArrayList<>();

    @OneToMany(mappedBy = "idSeguidor", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Seguimiento> seguidor = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<UsuarioGrupo> usuarioGrupo = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "preferenciaUsuario",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id"))
    private List<Genero> preferenciaUsuario = new ArrayList<>();

    public void addPreferenciaUsuario(Genero genero){
        if(preferenciaUsuario == null){
            preferenciaUsuario = new ArrayList<>();
        }
        if(genero != null){
            preferenciaUsuario.add(genero);
        }
    }

    public void removePreferenciaUsuario(Genero genero) {
        if(preferenciaUsuario != null && genero != null) {
            preferenciaUsuario.remove(genero);
        }
    }
}
