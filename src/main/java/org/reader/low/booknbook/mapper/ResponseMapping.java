package org.reader.low.booknbook.mapper;

import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.object.*;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.PaginationInfo;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.reader.low.booknbook.controller.response.libro.LibroPerfil;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;
import org.reader.low.booknbook.model.bnb.*;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.reader.low.booknbook.utils.ApplicationUtils.verifyObjectInstance;

@Slf4j
public class ResponseMapping {

    public static AutorPerfilResponse mapToAutorPerfilResponse(Autor autor) throws SQLException, IOException {
        return AutorPerfilResponse.builder()
                .id(autor.getId())
                .pseudonimo(autor.getPseudonimo())
                .imagen(autor.getFotoAutor())
                .biografia(autor.getBiografia())
                .localidad(autor.getLocalidad())
                .build();
    }

    public static AutorPerfilLibrosResponse mapToAutorPerfilLibrosResponse(List<LibroObject> listaLibros){
        return AutorPerfilLibrosResponse.builder().libros(listaLibros).build();
    }

    public static List<LibroObject> mapToListLibroObject(Autor autor) {
        return autor.getLibros().stream()
                .map(ResponseMapping::mapToLibroObject)
                .collect(toList());
    }

    private static LibroObject mapToLibroObject(Libro libro) {
        //calculamos la calificacion media de todos los usuarios

        return LibroObject.builder()
                .id(libro.getId())
                .nombre(libro.getNombre())
                .descripcion(libro.getDescripcion())
                .fechaPublicacion(libro.getFechaPublicacion())
                .fotoLibro(libro.getFotoLibro())
                .saga(Objects.isNull(libro.getSaga()) ? null : libro.getSaga().getNombre())
                .grupos(libro.getLibroGrupo().size())
                .valoracion(BigDecimal.valueOf(libro.getValoracion().size() > 0 ?
                        libro.getValoracion().stream()
                                .mapToDouble(Valoracion::getCalificacionPersonal).sum() / libro.getValoracion().size() : 0))
                .comentarios(libro.getValoracion().size())
                .build();
    }

    public static IdResponse mapToIdResponseGrupo(Grupo grupo) {
        return mapToIdResponse(grupo.getId(), "Grupo guardado Correctamente.");
    }

    public static ListGrupoResponse mapToListGroupResponse(List<Grupo> grupos, PaginationInfo pageInfo, boolean needToken) {
        return ListGrupoResponse.builder().listGroup(listGroupDescription(grupos, needToken))
                .pageInfo(pageInfo).build();
    }

    public static LibroPerfil mapToLibroPerfil(Libro libro) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(libro.getFechaPublicacion());
        return LibroPerfil.builder()
                .id(libro.getId())
                .titulo(libro.getNombre())
                .saga(libro.getSaga() != null ? libro.getSaga().getNombre() : null)
                .idAutor(libro.getAutor() != null ? libro.getAutor().getId() : 0)
                .autor(libro.getAutor() != null ? libro.getAutor().getPseudonimo() : null)
                .imagen(libro.getFotoLibro())
                .paginasTotales(libro.getPagTotal())
                .anyo(libro.getFechaPublicacion())
                .genero(libro.getGenero() != null ? libro.getGenero().getNombre() : null)
                .tipo(libro.getTipo() != null ? libro.getTipo().getNombre() : null)
                .calificacionMedia(libro.getValoracion().size() > 0  ?
                        libro.getValoracion().stream()
                                .map(Valoracion::getCalificacionPersonal)
                                .reduce(0, Integer::sum)/libro.getValoracion().size() :
                        0)
                .descripcion(libro.getDescripcion())
                .build();
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

    public static ListNameGrupoResponse mapToListNameGroupResponse(List<UsuarioGrupo> usersGroup, String type, List<UsuarioGrupo> userGroupList, Pageable pageable){
        return ListNameGrupoResponse.builder()
                .nombreGrupos(mapToListNombreGrupos(usersGroup, type))
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, userGroupList))
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
                .administrador(Constants.A.equals(type) && "SIR".equals(userGrupo.getRol()))
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
                .id(libro.getId())
                .imagen(libro.getFotoLibro())
                .saga(Objects.isNull(libro.getSaga()) ? null : libro.getSaga().getNombre())
                .titulo(libro.getNombre())
                .autor(Objects.isNull(libro.getAutor()) ? null: libro.getAutor().getPseudonimo())
                .build();
    }

    public static PaginationInfo mapToPaginationInfo(Pageable pageable, List lista){
        Integer div = Math.floorDiv(lista.size(), pageable.getPageSize());
        Integer divNoEq0 = pageable.getPageSize()>0 && lista.size()%pageable.getPageSize() == 0 ?
                div : div+1;
        Integer numberPages =  pageable.getPageSize()> 0 ?
                pageable.getPageSize() > lista.size() ? 1 :
                        divNoEq0 : 1;
        return PaginationInfo.builder()
                .elementsPerPage(pageable.getPageSize())
                .firstPage(1)
                .lastPage(numberPages)
                .totalElements(lista.size())
                .totalPages(numberPages)
                .actualPage(pageable.getPageNumber()+1)
                .build();
    }

    public static UserInfoResponse mapToUserInfoResponse(List<Usuario> usuarios, PaginationInfo pageInfo) {
        return UserInfoResponse.builder()
                .usuarios(mapToListUserInfo(usuarios))
                .pageInfo(pageInfo)
                .build();
    }

    private static List<UserInfo> mapToListUserInfo(List<Usuario> usuarios) {
        return usuarios.stream().map(ResponseMapping::mapToUserInfo).toList();
    }

    private static UserInfo mapToUserInfo(Usuario usuario) {
        return  UserInfo.builder()
                .imagenUsuario(usuario.getFotoPerfil())
                .username(usuario.getNombreUsuario())
                .nombre(usuario.getNombre())
                .apellido1(usuario.getApellido1())
                .apellido2(usuario.getApellido2())
                .email(usuario.getCorreo())
                .rol(usuario.getRol())
                .build();
    }

    public static List<LibroGestion> mapToListLibroGestionResponse(List<Libro> libros) {
        return libros.stream().map(ResponseMapping::mapToLibroGestion).toList();
    }

    private static LibroGestion mapToLibroGestion(Libro libro) {
        return LibroGestion.builder()
                .id(libro.getId())
                .imagen(libro.getFotoLibro())
                .saga(libro.getSaga() != null ? libro.getSaga().getNombre() : null)
                .titulo(libro.getNombre())
                .autor(libro.getAutor() != null ? libro.getAutor().getPseudonimo() : "Anonimo")
                .genero(libro.getGenero() != null ? libro.getGenero().getNombre() : null)
                .tipo(libro.getTipo() != null ? libro.getTipo().getNombre() : null)
                .year(libro.getFechaPublicacion())
                .build();
    }

    public static ValoracionResponse mapToValoracionResponse(Valoracion valoracionSaved) {
        return ValoracionResponse.builder()
                .idLibro(valoracionSaved.getLibro().getId())
                .paginaActual(valoracionSaved.getPaginaActual())
                .calificacionPersonal(valoracionSaved.getCalificacionPersonal())
                .comentario(valoracionSaved.getComentario())
                .fechaLectura(valoracionSaved.getFechaLectura())
                .fechaComentario(valoracionSaved.getFechaComentario())
                .estado(valoracionSaved.getEstado())
                .build();
    }

    public static void mapToValoracionUpdate(Valoracion valoracionSaved, ValoracionResponse request) {
        valoracionSaved.setEstado(request.getEstado());
        valoracionSaved.setComentario(request.getComentario());
        if(request.getCalificacionPersonal() == null || request.getCalificacionPersonal() == 0){
            valoracionSaved.setCalificacionPersonal(null);
        }else {
            valoracionSaved.setCalificacionPersonal(request.getCalificacionPersonal());
        }

        valoracionSaved.setFechaComentario(new Date(request.getFechaComentario().getTime()));
        valoracionSaved.setFechaLectura(new Date(request.getFechaLectura().getTime()));
        valoracionSaved.setPaginaActual(request.getPaginaActual());
    }
}
