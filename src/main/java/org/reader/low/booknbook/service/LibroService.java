package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.model.bnb.Libro;

public interface LibroService {

    void puntuarLibro(PuntuarLibroRequest request);

    Libro crearLibro(CreateLibroRequest request);

    ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String filter);


}
