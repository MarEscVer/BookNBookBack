package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.denuncia.DenunciaRequest;
import org.reader.low.booknbook.controller.response.IdResponse;

import java.util.List;

public interface DenunciaService {

    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado);

    void estadoDenuncia(Long idDenuncia, String estado);

    IdResponse hacerDenuncia(DenunciaRequest request);
}
