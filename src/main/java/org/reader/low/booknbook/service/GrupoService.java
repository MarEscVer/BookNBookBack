package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface Grupo service.
 */
public interface GrupoService {

    /**
     * Gets list group.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param filter    the filter
     * @param needToken the need token
     * @return the list group
     */
    ListGrupoResponse getListGroup(Integer pageIndex, Integer size, String filter, boolean needToken);

    /**
     * Gets list grupo propios.
     *
     * @param type      the type
     * @param pageIndex the page index
     * @param size      the size
     * @param filter    the filter
     * @return the list grupo propios
     */
    ListNameGrupoResponse getListGrupoPropios(String type, Integer pageIndex, Integer size, String filter);

    DeleteResponse deleteGroup(Long idGrupo);

    /**
     * Create group id response.
     *
     * @param createGroupRequest the create group request
     * @return the id response
     * @throws IOException the io exception
     */
    IdResponse createGroup(CreateGroupRequest createGroupRequest) throws IOException;

    /**
     * Sets image to group.
     *
     * @param idGrupo the id grupo
     * @param imagen  the imagen
     * @throws IOException the io exception
     */
    void setImageToGroup(Long idGrupo, MultipartFile imagen) throws IOException;

    /**
     * Pertenecer id response.
     *
     * @param idGrupo the id grupo
     * @return the id response
     */
    IdResponse pertenecer(Long idGrupo);

    /**
     * Abandonar id response.
     *
     * @param idGrupo the id grupo
     * @return the id response
     */
    IdResponse abandonar(Long idGrupo);
}
