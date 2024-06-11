package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.response.usuario.CalendarioUsuarioResponse;
import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Sets imagen usuario.
     *
     * @param imagen the imagen
     * @throws IOException the io exception
     */
    void setImagenUsuario(MultipartFile imagen) throws IOException;

    /**
     * Update rol usuario.
     *
     * @param request the request
     */
    void updateRolUsuario(RolRequest request);

    /**
     * Update estado usuario id response.
     *
     * @param estado the estado
     * @return the id response
     */
    IdResponse updateEstadoUsuario(boolean estado);

    /**
     * Gets list usuario.
     *
     * @param username  the username
     * @param pageIndex the page index
     * @param size      the size
     * @return the list usuario
     */
    UserInfoResponse getListUsuario(String username, Integer pageIndex, Integer size);

    /**
     * Gets perfil usuario.
     *
     * @param username the username
     * @return the perfil usuario
     */
    PerfilUsuario getPerfilUsuario(String username);

    /**
     * Update perfil usuario username response.
     *
     * @param request the request
     * @return the username response
     */
    UsernameResponse updatePerfilUsuario(UpdatePerfilUsuario request);

    /**
     * Gets list valoracion perfil.
     *
     * @param username the username
     * @return the list valoracion perfil
     */
    ValoracionPerfilUsuarioResponse getListValoracionPerfil(String username);

    /**
     * Delete usuario username response.
     *
     * @param username the username
     * @param self     the self
     * @return the username response
     */
    UsernameResponse deleteUsuario(String username, boolean self);

    /**
     * Libros propios libros propios usuario response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param estado    the estado
     * @return the libros propios usuario response
     */
    LibrosPropiosUsuarioResponse librosPropios(Integer pageIndex, Integer size,String estado);

    /**
     * Seguir usuario id response.
     *
     * @param username the username
     * @param accion   the accion
     * @return the id response
     */
    IdResponse seguirUsuario(String username, boolean accion);

    /**
     * Estadistica contador usuario response.
     *
     * @return the contador usuario response
     */
    ContadorUsuarioResponse estadistica();

    /**
     * Estadistica genero estadistica genero response.
     *
     * @return the estadistica genero response
     */
    EstadisticaGeneroResponse estadisticaGenero();

    /**
     * Estadistica estado libro estadistica genero response.
     *
     * @return the estadistica genero response
     */
    EstadisticaGeneroResponse estadisticaEstadoLibro();

    /**
     * Estadistica calendario calendario usuario response.
     *
     * @param anyoSelected the anyo selected
     * @return the calendario usuario response
     */
    CalendarioUsuarioResponse estadisticaCalendario(Integer anyoSelected);
}
