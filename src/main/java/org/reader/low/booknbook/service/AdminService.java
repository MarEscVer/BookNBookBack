package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.denuncia.MessageValoracion;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Admin service.
 */
public interface AdminService {
    /**
     * Create libro id response.
     *
     * @param createLibroRequest the create libro request
     * @return the id response
     */
    IdResponse createLibro(CreateLibroRequest createLibroRequest);

    /**
     * Create autor id response.
     *
     * @param createAutorRequest the create autor request
     * @return the id response
     */
    IdResponse createAutor(CreateAutorRequest createAutorRequest);

    /**
     * Lista comentarios denunciados list.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param filter    the filter
     * @param estado    the estado
     * @return the list
     */
    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado);

    /**
     * Estado denuncia.
     *
     * @param idDenuncia the id denuncia
     * @param estado     the estado
     */
    void estadoDenuncia(Long idDenuncia, String estado);

    /**
     * Sets imagen libro.
     *
     * @param idLibro the id libro
     * @param imagen  the imagen
     * @throws IOException the io exception
     */
    void setImagenLibro(Long idLibro, MultipartFile imagen) throws IOException;

    /**
     * Sets imagen autor.
     *
     * @param idAutor the id autor
     * @param imagen  the imagen
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void setImagenAutor(Long idAutor, MultipartFile imagen) throws IOException, SQLException;

    /**
     * Valoracion message message valoracion.
     *
     * @param idLibro   the id libro
     * @param idUsuario the id usuario
     * @return the message valoracion
     */
    MessageValoracion valoracionMessage(Long idLibro, Long idUsuario);

    /**
     * Gets usuario info.
     *
     * @param username  the username
     * @param pageIndex the page index
     * @param size      the size
     * @return the usuario info
     */
    UserInfoResponse getUsuarioInfo(String username, Integer pageIndex, Integer size);

    /**
     * Gets list libros.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param filtro    the filtro
     * @return the list libros
     */
    ListLibroGestionResponse getListLibros(Integer pageIndex, Integer size, String filtro);

    /**
     * Update libro id response.
     *
     * @param request the request
     * @return the id response
     */
    IdResponse updateLibro(UpdateLibroRequest request);

    /**
     * Update autor id response.
     *
     * @param updateAutorRequest the update autor request
     * @return the id response
     */
    IdResponse updateAutor(UpdateAutorRequest updateAutorRequest);

    /**
     * Delete libro id response.
     *
     * @param idLibro the id libro
     * @return the id response
     */
    IdResponse deleteLibro(Long idLibro);
}
