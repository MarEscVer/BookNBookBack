package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.model.bnb.*;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;

/**
 * The type Repository mapping.
 */
public class RepositoryMapping {

    /**
     * Map to usuario register usuario.
     *
     * @param register the register
     * @return the usuario
     */
    public static Usuario mapToUsuarioRegister(RegisterRequest register) {
        return Usuario.builder()
                .nombreUsuario(register.getUsuario())
                .nombre(register.getNombre())
                .apellido1(register.getApellidoPrimero())
                .apellido2(register.getApellidoSegundo())
                .correo(register.getEmail())
                .estado(true)
                .password(new BCryptPasswordEncoder().encode(register.getPassword()))
                .rol("NORMAL")
                .build();
    }

    /**
     * Map to usuario grupo usuario grupo.
     *
     * @param grupo   the grupo
     * @param usuario the usuario
     * @param rol     the rol
     * @param estado  the estado
     * @return the usuario grupo
     */
    public static UsuarioGrupo mapToUsuarioGrupo(Grupo grupo, Usuario usuario, String rol, String estado) {
        return UsuarioGrupo.builder()
                .id(new IdUsuarioGrupo(grupo.getId(), usuario.getId()))
                .grupo(grupo).rol(rol).usuario(usuario)
                .estado(estado)
                .build();
    }

    /**
     * Map to group grupo.
     *
     * @param createGroupRequest the create group request
     * @return the grupo
     */
    public static Grupo mapToGroup(CreateGroupRequest createGroupRequest) {
        return Grupo.builder()
                .nombre(createGroupRequest.getNombreGrupo())
                .descripcion(createGroupRequest.getDescripcion())
                .estado("ACTIVO")
                .build();
    }

    /**
     * Map to valoracion valoracion.
     *
     * @param request the request
     * @param usuario the usuario
     * @param libro   the libro
     * @return the valoracion
     */
    public static Valoracion mapToValoracion(PuntuarLibroRequest request, Usuario usuario, Libro libro) {
        return Valoracion.builder()
                .id(IdValoracion.builder().idLibro(libro.getId()).idUsuario(usuario.getId()).build())
                .estado("LEIDO")
                .libro(libro)
                .usuario(usuario)
                .paginaActual(libro.getPagTotal())
                .calificacionPersonal(request.getPuntuacion())
                .comentario(request.getComentario())
                .build();
    }

    /**
     * Map to libro create libro.
     *
     * @param request the request
     * @param libro   the libro
     * @return the libro
     */
    public static Libro mapToLibroCreate(CreateLibroRequest request, Libro libro) {
        Libro.LibroBuilder lib = libro.toBuilder();
        return lib
                .descripcion(request.getDescripcion())
                .fechaPublicacion(new Date(request.getFechaPublicacion().getTime()))
                .nombre(request.getNombre())
                .estado("ACTIVO")
                .pagTotal(request.getPaginas())
                .build();
    }

    /**
     * Map to libro libro.
     *
     * @param request the request
     * @param libro   the libro
     * @return the libro
     */
    public static Libro mapToLibro(UpdateLibroRequest request, Libro libro){
        libro.setDescripcion(request.getDescripcion());
        libro.setFechaPublicacion(new Date(request.getFechaPublicacion().getTime()));
        libro.setNombre(request.getNombre());
        libro.setPagTotal(request.getPaginas());
        return libro;
    }

    /**
     * Map to autor autor.
     *
     * @param createAutorRequest the create autor request
     * @return the autor
     */
    public static Autor mapToAutor(CreateAutorRequest createAutorRequest) {
        return Autor.builder().pseudonimo(createAutorRequest.getPseudonimo())
                .biografia(createAutorRequest.getBiografia())
                .localidad(createAutorRequest.getLocalidad()).build();
    }

    /**
     * Map to valoracion valoracion.
     *
     * @param libro   the libro
     * @param usuario the usuario
     * @param estado  the estado
     * @return the valoracion
     */
    public static Valoracion mapToValoracion(Libro libro, Usuario usuario, String estado) {
        return Valoracion.builder()
                .id(IdValoracion.builder().idUsuario(usuario.getId()).idLibro(libro.getId()).build())
                .estado(estado.toUpperCase()).libro(libro).usuario(usuario)
                .fechaLectura(Date.valueOf(LocalDate.now()))
                .build();
    }
}
