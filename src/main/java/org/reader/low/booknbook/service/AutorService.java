package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;

import java.io.IOException;
import java.sql.SQLException;

public interface AutorService {

    AutorPerfilResponse getAutorPerfil(Long idAutor) throws SQLException, IOException;

    IdResponse crearAutor(CreateAutorRequest createAutorRequest);

    AutorPerfilLibrosResponse getAutorPerfilLibros(Long idAutor);

    IdResponse updateAutor(UpdateAutorRequest updateAutorRequest);
}
