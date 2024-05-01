package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "rol", nullable=false, columnDefinition = "varchar(25) default 'NORMAL'")
    private String rol;

    @Column(name = "password", nullable=false)
    @Size(max = 300)
    private String password;

    @Lob
    @Column(name = "fotoPerfil", columnDefinition="LONGBLOB")
    private byte[] fotoPerfil;

    @Column(name = "estado", nullable=false, columnDefinition = "boolean default true")
    private boolean estado;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<PaginasLibro> paginasLibro;

    @OneToMany(mappedBy = "idSeguido", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Seguimiento> seguido;

    @OneToMany(mappedBy = "idSeguidor", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Seguimiento> seguidor;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<UsuarioGrupo> usuarioGrupo;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Valoracion> valoracion;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<ComentarioGrupo> comentarioGrupo;

    @ManyToMany(mappedBy = "preferenciaUsuario",fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    Set<Genero> preferenciaUsuario;

}
