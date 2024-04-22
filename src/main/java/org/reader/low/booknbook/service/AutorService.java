package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.autor.AutorPerfilRequest;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;

public interface AutorService {

    AutorPerfilResponse getAutorPerfil(AutorPerfilRequest request);
}
