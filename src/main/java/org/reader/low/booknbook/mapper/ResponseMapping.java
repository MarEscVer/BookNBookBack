package org.reader.low.booknbook.mapper;

import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.object.*;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.reader.low.booknbook.model.bnb.*;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.reader.low.booknbook.utils.ApplicationUtils.verifyObjectInstance;

@Slf4j
public class ResponseMapping {

    public static AutorPerfilResponse mapToAutorPerfilResponse(Autor autor, List<LibroObject> listaLibros) {
        return AutorPerfilResponse.builder()
                .pseudonimo(autor.getPseudonimo())
                .imagen(autor.getFotoAutor())
                .biografia(autor.getBiografia())
                .libros(listaLibros)
                .build();
    }

    public static List<LibroObject> mapToListLibroObject(Autor autor) {
        return autor.getLibro().stream()
                .map(ResponseMapping::mapToLibroObject)
                .collect(toList());
    }

    private static LibroObject mapToLibroObject(Libro libro) {
        return LibroObject.builder()
                .id(libro.getId())
                .nombre(libro.getNombre())
                .descripcion(libro.getDescripcion())
                .fechaPublicacion(libro.getFechaPublicacion())
                .fotoLibro(libro.getFotoLibro())
                .saga(libro.getSaga().getNombre())
                .grupos(libro.getLibroGrupo().size())
                .valoracion(BigDecimal.valueOf(libro.getValoracion().size() > 0 ?
                        libro.getValoracion().stream()
                                .mapToDouble(value ->
                                        //calculamos la calificacion media de todos los usuarios
                                        value.getCalificacionPersonal()).sum() / libro.getValoracion().size() : 0))
                .comentarios(libro.getValoracion().size())
                .build();
    }

    public static ListGrupoResponse mapToListGroupResponse(List<Grupo> grupos, boolean needToken) {
        return ListGrupoResponse.builder().listGroup(listGroupDescription(grupos, needToken)).build();
    }

    public static List<GroupDescripcion> listGroupDescription(List<Grupo> grupos, boolean needToken){
        return grupos.stream()
                .map(grupo -> mapToGroupDescription(grupo, needToken))
                .collect(toList());
    }

    public static GroupDescripcion mapToGroupDescription(Grupo grupo, boolean needToken) {
        ComboGenero genero = null;
        if(verifyObjectInstance(grupo.getGenero())){
            genero = ComboGenero.builder().id(grupo.getGenero().getId()).nombre(grupo.getGenero().getNombre()).color("Green").build();
        }
        ComboGenero tipo = null;
        if(verifyObjectInstance(grupo.getTipo())){
            tipo = ComboGenero.builder().id(grupo.getTipo().getId()).nombre(grupo.getTipo().getNombre()).color("Red").build();
        }
        return GroupDescripcion.builder()
                .id(grupo.getId())
                .nombre(grupo.getNombre())
                .descripcion(grupo.getDescripcion())
                .genero(genero)
                .tipo(tipo)
                .miembros(grupo.getUsuarioGrupo().size())
                .perteneces(grupo.getUsuarioGrupo().stream()
                        //comprobacion para no llamar al metodo que utiliza token siendo anonimo(llamada sin token)
                        .filter((UsuarioGrupo usuario) ->
                                needToken ?
                                    usuario.getUsuario().getNombreUsuario().equals(SecurityUtils.getUsername()) :
                            false
                        ).toList().size()>0)
                .imagen(grupo.getImagen())
                .build();
    }

    public static ListNameGrupoResponse mapToListNameGroupResponse(List<UsuarioGrupo> usersGroup, String type){
        return ListNameGrupoResponse.builder()
                .nombreGrupos(mapToListNombreGrupos(usersGroup, type))
                .build();
    }

    private static List<NombreGrupos> mapToListNombreGrupos(List<UsuarioGrupo> usersGroup, String type){
        return usersGroup.stream().map(simple -> mapToNombreGrupos(simple, type)).toList();
    }

    private static NombreGrupos mapToNombreGrupos(UsuarioGrupo userGrupo, String type) {
        Grupo grupo = userGrupo.getGrupo();
        return NombreGrupos.builder()
                .nombreGrupo(grupo.getNombre())
                .idGrupo(grupo.getId())
                .imagen(grupo.getImagen())
                //Si es lista de administradas, es lider(puede eliminar) SIR
                .administrador(Constants.A.equals(type) && userGrupo.getRol().equals("SIR"))
                .build();
    }

    public static ModerateComments maptToModerateComments(Valoracion valoracion) {
        return ModerateComments.builder()
                .idDenuncia(valoracion.getDenuncia().getId())
                .idValoracionLibro(valoracion.getId().getIdLibro())
                .idValoracionUsuario(valoracion.getId().getIdUsuario())
                .nombreUsuario(valoracion.getUsuario().getNombreUsuario())
                .fechaDenuncia(valoracion.getDenuncia().getFecha())
                .comentario(valoracion.getDenuncia().getTexto())
                .motivo(valoracion.getDenuncia().getMotivo())
                .build();
    }

    public static IdResponse mapToIdResponse(Long id, String message) {
        return IdResponse.builder().id(id).message(message).build();
    }


    public static List<LibroDescripcion> mapToListLibroDescripcion(List<Libro> libros){
        return libros.stream().map(ResponseMapping::mapToLibroDescripcion).toList();
    }

    public static LibroDescripcion mapToLibroDescripcion(Libro libro){
        return LibroDescripcion.builder()
                .imagen(libro.getFotoLibro())
                .saga(libro.getSaga().getNombre())
                .titulo(libro.getNombre())
                .autor(libro.getAutor().getPseudonimo())
                .build();
    }
}
