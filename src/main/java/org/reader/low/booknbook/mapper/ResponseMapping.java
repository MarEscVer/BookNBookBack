package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.object.GroupDescripcion;
import org.reader.low.booknbook.controller.object.LibroObject;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.object.NombreGrupos;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.reader.low.booknbook.model.bnb.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.reader.low.booknbook.utils.ApplicationUtils.verifyObjectInstance;

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

    public static ListGrupoResponse mapToListGroupResponse(List<Grupo> grupos) {
        return ListGrupoResponse.builder().listGroup(listGroupDescription(grupos)).build();
    }

    public static List<GroupDescripcion> listGroupDescription(List<Grupo> grupos){
        return grupos.stream()
                .map(ResponseMapping::mapToGroupDescription)
                .collect(toList());
    }

    public static GroupDescripcion mapToGroupDescription(Grupo grupo) {
        Map<String, String> genero = new HashMap<>();
        if(verifyObjectInstance(grupo.getGenero())){
            genero.put(grupo.getGenero().getNombre(), "Green");
        }
        Map<String, String> tipo = new HashMap<>();
        if(verifyObjectInstance(grupo.getTipo())){
            genero.put(grupo.getTipo().getNombre(), "Red");
        }
        return GroupDescripcion.builder()
                .id(grupo.getId())
                .nombre(grupo.getNombre())
                .descripcion(grupo.getDescripcion())
                .genero(genero)
                .tipo(tipo)
                .miembros(grupo.getUsuarioGrupo().size())
                .perteneces(grupo.getUsuarioGrupo().stream()
                        .filter((UsuarioGrupo usuario) ->
                                usuario.getUsuario().getNombreUsuario().equals(SecurityUtils.getUsername()))
                        .toList().size()>0)
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
}
