package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The interface Autor service.
 */
public interface AutorService {

    /**
     * Gets autor perfil.
     *
     * @param idAutor the id autor
     * @return the autor perfil
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    AutorPerfilResponse getAutorPerfil(Long idAutor) throws SQLException, IOException;

    /**
     * Crear autor id response.
     *
     * @param createAutorRequest the create autor request
     * @return the id response
     */
    IdResponse crearAutor(CreateAutorRequest createAutorRequest);

    /**
     * Gets autor perfil libros.
     *
     * @param idAutor the id autor
     * @return the autor perfil libros
     */
    AutorPerfilLibrosResponse getAutorPerfilLibros(Long idAutor);

    /**
     * Update autor id response.
     *
     * @param updateAutorRequest the update autor request
     * @return the id response
     */
    IdResponse updateAutor(UpdateAutorRequest updateAutorRequest);
}
