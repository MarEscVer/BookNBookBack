package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.libro.ComentarioPerfilLibroResponse;
import org.reader.low.booknbook.controller.response.libro.LibroPerfil;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;

/**
 * The interface Libro service.
 */
public interface LibroService {

    /**
     * Puntuar libro.
     *
     * @param request the request
     */
    void puntuarLibro(PuntuarLibroRequest request);

    /**
     * Crear libro id response.
     *
     * @param request the request
     * @return the id response
     */
    IdResponse crearLibro(CreateLibroRequest request);

    /**
     * Gets list recomendados.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the list recomendados
     */
    ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String genero);

    /**
     * Gets list libros novedades.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the list libros novedades
     */
    ListaLibrosRecomendadosResponse getListLibrosNovedades(Integer pageIndex, Integer size, String genero);

    /**
     * Gets list libros leidos.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the list libros leidos
     */
    ListaLibrosRecomendadosResponse getListLibrosLeidos(Integer pageIndex, Integer size, String genero);

    /**
     * Gets list libros aleatorios.
     *
     * @param size   the size
     * @param genero the genero
     * @return the list libros aleatorios
     */
    ListaLibrosRecomendadosResponse getListLibrosAleatorios(Integer size, String genero);

    /**
     * Gets list libros gestion.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param filtro    the filtro
     * @return the list libros gestion
     */
    ListLibroGestionResponse getListLibrosGestion(Integer pageIndex, Integer size, String filtro);

    /**
     * Update libro id response.
     *
     * @param request the request
     * @return the id response
     */
    IdResponse updateLibro(UpdateLibroRequest request);

    /**
     * Guardar libro valoracion valoracion response.
     *
     * @param idLibro the id libro
     * @param estado  the estado
     * @return the valoracion response
     */
    ValoracionResponse guardarLibroValoracion(Long idLibro, String estado);

    /**
     * Actualizar libro valoracion valoracion response.
     *
     * @param request the request
     * @return the valoracion response
     */
    ValoracionResponse actualizarLibroValoracion(ValoracionResponse request);

    /**
     * Gets list libros favoritos usuario.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param username  the username
     * @param filtro    the filtro
     * @return the list libros favoritos usuario
     */
    PerfilUsuarioLibrosFavoritosResponse getListLibrosFavoritosUsuario(Integer pageIndex, Integer size, String username, String filtro);

    /**
     * Gets libro perfil.
     *
     * @param idLibro the id libro
     * @return the libro perfil
     */
    LibroPerfil getLibroPerfil(Long idLibro);

    /**
     * Gets comentarios libro.
     *
     * @param idLibro   the id libro
     * @param pageIndex the page index
     * @param size      the size
     * @return the comentarios libro
     */
    ComentarioPerfilLibroResponse getComentariosLibro(Long idLibro, Integer pageIndex, Integer size);

    /**
     * Gets listlibros.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @param filter    the filter
     * @return the listlibros
     */
    ListaLibrosRecomendadosResponse getListlibros(Integer pageIndex, Integer size, String genero, String filter);
}
