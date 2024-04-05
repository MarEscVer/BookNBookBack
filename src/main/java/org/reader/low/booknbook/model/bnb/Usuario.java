package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

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
    private String password;

    @Column(name = "fotoPerfil")
    private Blob fotoPerfil;

    @OneToMany(mappedBy = "usuario")
    private Set<PaginasLibro> paginasLibro;

    @OneToMany(mappedBy = "idSeguido")
    private Set<Seguimiento> seguido;

    @OneToMany(mappedBy = "idSeguidor")
    private Set<Seguimiento> seguidor;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioGrupo> usuarioGrupo;

    @OneToMany(mappedBy = "usuario")
    private Set<Valoracion> valoracion;

    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioGrupo> comentarioGrupo;

    @ManyToMany(mappedBy = "preferenciaUsuario")
    Set<Genero> preferenciaUsuario;

}
