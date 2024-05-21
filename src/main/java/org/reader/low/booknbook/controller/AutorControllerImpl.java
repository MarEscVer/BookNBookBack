package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.AutorController;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/autor")
public class AutorControllerImpl implements AutorController {

    @Autowired
    private AutorService autorService;

    @Override
    public AutorPerfilResponse getperfilAutor(Long idAutor) throws SQLException, IOException {
        return autorService.getAutorPerfil(idAutor);
    }

    @Override
    public AutorPerfilLibrosResponse getperfilAutorLibros(Long idAutor) throws SQLException, IOException {
        return autorService.getAutorPerfilLibros(idAutor);
    }
}
