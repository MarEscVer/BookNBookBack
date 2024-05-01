package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.model.bnb.*;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

public class RepositoryMapping {

    public static Usuario mapToUsuarioRegister(RegisterRequest register) {
        return Usuario.builder()
                .nombreUsuario(register.getUsuario())
                .nombre(register.getNombre())
                .apellido1(register.getApellidoPrimero())
                .apellido2(register.getApellidoSegundo())
                .correo(register.getEmail())
                .password(new BCryptPasswordEncoder().encode(register.getPassword()))
                .rol("NORMAL")
                .build();
    }

    public static UsuarioGrupo mapToUsuarioGrupo(Grupo grupo, Usuario usuario, String rol) {
        return UsuarioGrupo.builder()
                .id(new IdUsuarioGrupo(grupo.getId(), usuario.getId()))
                .grupo(grupo).rol(rol).usuario(usuario)
                .build();
    }

    public static Grupo mapToGroup(CreateGroupRequest createGroupRequest, Genero genero, Genero tipo) {
        return Grupo.builder()
                .nombre(createGroupRequest.getNombreGrupo())
                .tipo(createGroupRequest.getTipo() != null && createGroupRequest.getTipo() != 0 ? tipo : null)
                .genero(createGroupRequest.getGenero() != null && createGroupRequest.getGenero() != 0 ? genero : null)
                .descripcion(createGroupRequest.getDescripcion())
                .build();
    }

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

    public static Libro mapToLibro(CreateLibroRequest request, Autor autor, Genero genero, Genero tipo, Saga saga) {
        return Libro.builder()
                .descripcion(request.getDescripcion())
                .fechaPublicacion(new Date(request.getFechaPublicacion().getTime()))
                .nombre(request.getNombre())
                .pagTotal(request.getPaginas())
                .genero(genero)
                .tipo(tipo)
                .saga(saga)
                .autor(autor)
                .build();
    }

    public static Autor mapToAutor(CreateAutorRequest createAutorRequest) {
        return Autor.builder().pseudonimo(createAutorRequest.getPseudonimo())
                .biografia(createAutorRequest.getBiografia())
                .localidad(createAutorRequest.getLocalidad()).build();
    }
}
