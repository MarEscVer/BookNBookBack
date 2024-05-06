package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;

public interface AutorService {

    AutorPerfilResponse getAutorPerfil(Long idAutor);

    IdResponse crearAutor(CreateAutorRequest createAutorRequest);
}
