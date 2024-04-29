package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;

import java.util.List;

public interface AdminService {
    void createLibro(CreateLibroRequest createLibroRequest);

    void createAutor(CreateAutorRequest createAutorRequest);
    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter);
}
