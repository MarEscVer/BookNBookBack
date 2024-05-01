package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;

public interface LibroService {

    void puntuarLibro(PuntuarLibroRequest request);

    void crearLibro(CreateLibroRequest request);

    ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String filter);


}
