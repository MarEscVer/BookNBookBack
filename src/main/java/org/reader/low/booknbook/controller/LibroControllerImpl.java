package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.LibroController;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/libro")
public class LibroControllerImpl implements LibroController {

    @Autowired
    private LibroService libroService;

    @Override
    public void puntuarLibro(PuntuarLibroRequest puntuarLibroRequest) {
        libroService.puntuarLibro(puntuarLibroRequest);
    }

    @Override
    public ValoracionResponse guardarLibroValoracion(Long idLibro, String estado){
        return libroService.guardarLibroValoracion(idLibro, estado);
    }

    @Override
    public ValoracionResponse actualizarLibroValoracion(ValoracionResponse request) {
        return libroService.actualizarLibroValoracion(request);
    }


}
