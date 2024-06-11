package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Usuario.
 */
@Entity
@Table(name = "usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Nombre usuario.
     */
    @Column(name = "nombreUsuario", nullable=false)
    private String nombreUsuario;

    /**
     * The Nombre.
     */
    @Column(name = "nombre", nullable=false)
    private String nombre;

    /**
     * The Apellido 1.
     */
    @Column(name = "apellido1", nullable=false)
    private String apellido1;

    /**
     * The Apellido 2.
     */
    @Column(name = "apellido2")
    private String apellido2;

    /**
     * The Correo.
     */
    @Column(name = "correo", nullable=false)
    private String correo;

    /**
     * The Rol.
     */
    @ColumnDefault(value = "'NORMAL'")
    @Column(name = "rol", nullable=false)
    private String rol;

    /**
     * The Password.
     */
    @Column(name = "password", nullable=false)
    @Size(max = 300)
    private String password;

    /**
     * The Foto perfil.
     */
    @Lob
    @Column(name = "fotoPerfil", columnDefinition = "LONGBLOB")
    private byte[] fotoPerfil;

    /**
     * The Estado.
     */
    @Column(name = "estado", nullable=false, columnDefinition = "BOOLEAN default true")
    private boolean estado;

    /**
     * The Paginas libro.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<PaginasLibro> paginasLibro = new ArrayList<>();

    /**
     * The Seguido.
     */
    @OneToMany(mappedBy = "idSeguido", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Seguimiento> seguido = new ArrayList<>();

    /**
     * The Seguidor.
     */
    @OneToMany(mappedBy = "idSeguidor", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Seguimiento> seguidor = new ArrayList<>();

    /**
     * The Usuario grupo.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<UsuarioGrupo> usuarioGrupo = new ArrayList<>();

    /**
     * The Valoracion.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

    /**
     * The Comentario grupo.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

    /**
     * The Preferencia usuario.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "preferenciaUsuario",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id"))
    private List<Genero> preferenciaUsuario = new ArrayList<>();

    /**
     * Add preferencia usuario.
     *
     * @param genero the genero
     */
    public void addPreferenciaUsuario(Genero genero){
        if(preferenciaUsuario == null){
            preferenciaUsuario = new ArrayList<>();
        }
        if(genero != null){
            preferenciaUsuario.add(genero);
        }
    }

    /**
     * Remove preferencia usuario.
     *
     * @param genero the genero
     */
    public void removePreferenciaUsuario(Genero genero) {
        if(preferenciaUsuario != null && genero != null) {
            preferenciaUsuario.remove(genero);
        }
    }
}
