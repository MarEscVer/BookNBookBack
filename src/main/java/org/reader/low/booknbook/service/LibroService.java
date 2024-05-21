package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;

public interface LibroService {

    void puntuarLibro(PuntuarLibroRequest request);

    void crearLibro(CreateLibroRequest request);

    ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String genero);

    ListaLibrosRecomendadosResponse getListLibrosNovedades(Integer pageIndex, Integer size, String genero);

    ListaLibrosRecomendadosResponse getListLibrosLeidos(Integer pageIndex, Integer size, String genero);

    ListaLibrosRecomendadosResponse getListLibrosAleatorios(Integer size, String genero);

    ListLibroGestionResponse getListLibrosGestion(Integer pageIndex, Integer size, String filtro);

    IdResponse updateLibro(UpdateLibroRequest request);

    ValoracionResponse guardarLibroValoracion(Long idLibro, String estado);

    ValoracionResponse actualizarLibroValoracion(ValoracionResponse request);

    PerfilUsuarioLibrosFavoritosResponse getListLibrosFavoritosUsuario(Integer pageIndex, Integer size, String username, String filtro);

}
