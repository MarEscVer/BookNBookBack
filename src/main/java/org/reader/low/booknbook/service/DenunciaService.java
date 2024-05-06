package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;

import java.util.List;

public interface DenunciaService {

    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado);

    void estadoDenuncia(Long idDenuncia, String estado);
}
