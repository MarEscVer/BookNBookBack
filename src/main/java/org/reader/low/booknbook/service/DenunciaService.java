package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.denuncia.DenunciaRequest;
import org.reader.low.booknbook.controller.response.IdResponse;

import java.util.List;

/**
 * The interface Denuncia service.
 */
public interface DenunciaService {

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
     * Hacer denuncia id response.
     *
     * @param request the request
     * @return the id response
     */
    IdResponse hacerDenuncia(DenunciaRequest request);
}
