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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.reader.low.booknbook.utils.ApplicationUtils.verifyObjectInstance;

/**
 * The type Response mapping.
 */
@Slf4j
public class ResponseMapping {

    /**
     * Map to autor perfil response autor perfil response.
     *
     * @param autor the autor
     * @return the autor perfil response
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public static AutorPerfilResponse mapToAutorPerfilResponse(Autor autor) throws SQLException, IOException {
        return AutorPerfilResponse.builder()
                .id(autor.getId())
                .pseudonimo(autor.getPseudonimo())
                .imagen(autor.getFotoAutor())
                .biografia(autor.getBiografia())
                .localidad(autor.getLocalidad())
                .build();
    }

    /**
     * Map to autor perfil libros response autor perfil libros response.
     *
     * @param listaLibros the lista libros
     * @return the autor perfil libros response
     */
    public static AutorPerfilLibrosResponse mapToAutorPerfilLibrosResponse(List<LibroObject> listaLibros){
        return AutorPerfilLibrosResponse.builder().libros(listaLibros).build();
    }

    /**
     * Map to list libro object list.
     *
     * @param autor the autor
     * @return the list
     */
    public static List<LibroObject> mapToListLibroObject(Autor autor) {
        return autor.getLibros().stream()
                .map(ResponseMapping::mapToLibroObject)
                .collect(toList());
    }

    private static LibroObject mapToLibroObject(Libro libro) {
        //calculamos la calificacion media de todos los usuarios
        Integer calificacionesTotales = libro.getValoracion().stream()
                .filter(valoracion -> valoracion.getCalificacionPersonal() != null).toList().size();
        return LibroObject.builder()
                .id(libro.getId())
                .nombre(libro.getNombre())
                .descripcion(libro.getDescripcion())
                .fechaPublicacion(libro.getFechaPublicacion())
                .fotoLibro(libro.getFotoLibro())
                .saga(Objects.isNull(libro.getSaga()) ? null : libro.getSaga().getNombre())
                .grupos(libro.getLibroGrupo().size())
                .valoracion(BigDecimal.valueOf(calificacionesTotales > 0 ?
                        libro.getValoracion().stream()
                                .filter(valoracion -> valoracion.getCalificacionPersonal() != null)
                                .mapToDouble(Valoracion::getCalificacionPersonal).sum() / calificacionesTotales : 0))
                .comentarios(libro.getValoracion().size())
                .build();
    }

    /**
     * Map to id response grupo id response.
     *
     * @param grupo the grupo
     * @return the id response
     */
    public static IdResponse mapToIdResponseGrupo(Grupo grupo) {
        return mapToIdResponse(grupo.getId(), "Grupo guardado Correctamente.");
    }

    /**
     * Map to estadistica estadistica.
     *
     * @param entry the entry
     * @return the estadistica
     */
    public static Estadistica mapToEstadistica(Map.Entry<String, Long> entry) {
        return Estadistica.builder().titulo(entry.getKey()).dato(entry.getValue()).build();
    }

    /**
     * Map to list group response list grupo response.
     *
     * @param grupos    the grupos
     * @param pageInfo  the page info
     * @param needToken the need token
     * @return the list grupo response
     */
    public static ListGrupoResponse mapToListGroupResponse(List<Grupo> grupos, PaginationInfo pageInfo, boolean needToken) {
        return ListGrupoResponse.builder().listGroup(listGroupDescription(grupos, needToken))
                .pageInfo(pageInfo).build();
    }

    /**
     * Map to libro perfil libro perfil.
     *
     * @param libro the libro
     * @return the libro perfil
     */
    public static LibroPerfil mapToLibroPerfil(Libro libro) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(libro.getFechaPublicacion());
        Integer valoracionesTotales = libro.getValoracion().stream()
                .filter(valoracion -> valoracion.getCalificacionPersonal() != null).toList().size();

        return LibroPerfil.builder()
                .id(libro.getId())
                .titulo(libro.getNombre())
                .idSaga(libro.getSaga() != null ? libro.getSaga().getId() : null)
                .saga(libro.getSaga() != null ? libro.getSaga().getNombre() : null)
                .idAutor(libro.getAutor() != null ? libro.getAutor().getId() : 0)
                .autor(libro.getAutor() != null ? libro.getAutor().getPseudonimo() : null)
                .imagen(libro.getFotoLibro())
                .paginasTotales(libro.getPagTotal())
                .anyo(libro.getFechaPublicacion())
                .genero(libro.getGenero() != null ? Combo.builder().id(libro.getGenero().getId()).nombre(libro.getGenero().getNombre()).build() : null)
                .tipo(libro.getTipo() != null ? Combo.builder().id(libro.getTipo().getId()).nombre(libro.getTipo().getNombre()).build() : null)
                .calificacionMedia(BigDecimal.valueOf(valoracionesTotales > 0 ?
                        libro.getValoracion().stream()
                                .filter(valoracion -> valoracion.getCalificacionPersonal() != null)
                                .mapToDouble(Valoracion::getCalificacionPersonal).sum() / valoracionesTotales : 0))
                .descripcion(libro.getDescripcion())
                .contadorComentario(libro.getValoracion().stream().filter(val -> StringUtils.hasText(val.getComentario())).toList().size())
                .estado(SecurityUtils.getUsername() != null ?
                        libro.getValoracion().stream()
                                .filter(valoracion -> valoracion.getUsuario().getNombreUsuario().equals(SecurityUtils.getUsername()))
                                .findFirst().orElse(Valoracion.builder().build()).getEstado() : null)
                .build();
    }

    /**
     * List group description list.
     *
     * @param grupos    the grupos
     * @param needToken the need token
     * @return the list
     */
    public static List<GroupDescripcion> listGroupDescription(List<Grupo> grupos, boolean needToken){
        return grupos.stream()
                .map(grupo -> mapToGroupDescription(grupo, needToken))
                .collect(toList());
    }

    /**
     * Map to group description group descripcion.
     *
     * @param grupo     the grupo
     * @param needToken the need token
     * @return the group descripcion
     */
    public static GroupDescripcion mapToGroupDescription(Grupo grupo, boolean needToken) {
        ComboGenero genero = null;
        if(verifyObjectInstance(grupo.getGenero())){
            genero = ComboGenero.builder().id(grupo.getGenero().getId()).nombre(grupo.getGenero().getNombre()).color("Green").build();
        }
        ComboGenero tipo = null;
        if(verifyObjectInstance(grupo.getTipo())){
            tipo = ComboGenero.builder().id(grupo.getTipo().getId()).nombre(grupo.getTipo().getNombre()).color("Red").build();
        }
        List<UsuarioGrupo> usuarioGrupo = grupo.getUsuarioGrupo().stream()
                //comprobacion para no llamar al metodo que utiliza token siendo anonimo(llamada sin token)
                .filter((UsuarioGrupo usuario) ->
                        needToken ?
                                usuario.getUsuario().getNombreUsuario().equals(SecurityUtils.getUsername()) :
                                false
                )
                .toList();

        return GroupDescripcion.builder()
                .id(grupo.getId())
                .nombre(grupo.getNombre())
                .descripcion(grupo.getDescripcion())
                .genero(genero)
                .tipo(tipo)
                .miembros(grupo.getUsuarioGrupo().size())
                .perteneces(usuarioGrupo.size() > 0 && "ACTIVO".equals(usuarioGrupo.get(0).getEstado()))
                .imagen(grupo.getImagen())
                .build();
    }

    /**
     * Map to list name group response list name grupo response.
     *
     * @param usersGroup    the users group
     * @param type          the type
     * @param userGroupList the user group list
     * @param pageable      the pageable
     * @return the list name grupo response
     */
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

    /**
     * Mapt to moderate comments moderate comments.
     *
     * @param valoracion the valoracion
     * @return the moderate comments
     */
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

    /**
     * Map to id response id response.
     *
     * @param id      the id
     * @param message the message
     * @return the id response
     */
    public static IdResponse mapToIdResponse(Long id, String message) {
        return IdResponse.builder().id(id).message(message).build();
    }


    /**
     * Map to list libro descripcion list.
     *
     * @param libros the libros
     * @return the list
     */
    public static List<LibroDescripcion> mapToListLibroDescripcion(List<Libro> libros){
        return libros.stream().map(ResponseMapping::mapToLibroDescripcion).toList();
    }

    /**
     * Map to libro descripcion libro descripcion.
     *
     * @param libro the libro
     * @return the libro descripcion
     */
    public static LibroDescripcion mapToLibroDescripcion(Libro libro){
        return LibroDescripcion.builder()
                .id(libro.getId())
                .imagen(libro.getFotoLibro())
                .saga(Objects.isNull(libro.getSaga()) ? null : libro.getSaga().getNombre())
                .titulo(libro.getNombre())
                .autor(Objects.isNull(libro.getAutor()) ? null: libro.getAutor().getPseudonimo())
                .build();
    }

    /**
     * Map to pagination info pagination info.
     *
     * @param pageable the pageable
     * @param lista    the lista
     * @return the pagination info
     */
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

    /**
     * Map to user info response user info response.
     *
     * @param usuarios the usuarios
     * @param pageInfo the page info
     * @return the user info response
     */
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

    /**
     * Map to list libro gestion response list.
     *
     * @param libros the libros
     * @return the list
     */
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

    /**
     * Map to valoracion response valoracion response.
     *
     * @param valoracionSaved the valoracion saved
     * @return the valoracion response
     */
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

    /**
     * Map to valoracion update.
     *
     * @param valoracionSaved the valoracion saved
     * @param request         the request
     */
    public static void mapToValoracionUpdate(Valoracion valoracionSaved, ValoracionResponse request) {
        valoracionSaved.setEstado(request.getEstado());
        valoracionSaved.setComentario(request.getComentario());
        if(request.getCalificacionPersonal() == null || request.getCalificacionPersonal() == 0){
            valoracionSaved.setCalificacionPersonal(null);
        }else {
            valoracionSaved.setCalificacionPersonal(request.getCalificacionPersonal());
        }

        valoracionSaved.setFechaComentario(request.getFechaComentario());
        valoracionSaved.setFechaLectura(request.getFechaLectura());
        valoracionSaved.setPaginaActual(request.getPaginaActual());
    }

    /**
     * Map to lectura usuario lectura usuario.
     *
     * @param valoracion the valoracion
     * @return the lectura usuario
     */
    public static LecturaUsuario mapToLecturaUsuario(Valoracion valoracion) {
        return LecturaUsuario.builder()
                .id(valoracion.getLibro().getId())
                .autor(valoracion.getLibro().getAutor().getPseudonimo())
                .imagen(valoracion.getLibro().getFotoLibro())
                .titulo(valoracion.getLibro().getNombre())
                .descripcion(valoracion.getLibro().getDescripcion())
                .paginasTotales(valoracion.getLibro().getPagTotal())
                .saga(valoracion.getLibro().getSaga() != null ? valoracion.getLibro().getSaga().getNombre() : null)
                .genero(Combo.builder().id(valoracion.getLibro().getGenero() != null ? valoracion.getLibro().getGenero().getId() : null)
                        .nombre(valoracion.getLibro().getGenero() != null ? valoracion.getLibro().getGenero().getNombre() : null).build())
                .tipo(Combo.builder().id(valoracion.getLibro().getTipo() != null ? valoracion.getLibro().getTipo().getId() : null)
                        .nombre(valoracion.getLibro().getTipo() != null ? valoracion.getLibro().getTipo().getNombre() : null).build())
                .fechaLectura(valoracion.getFechaLectura())
                .paginasLeidas(valoracion.getPaginaActual())
                .build();
    }
}
