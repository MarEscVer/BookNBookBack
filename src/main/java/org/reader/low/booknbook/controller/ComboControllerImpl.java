package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.ComboController;
import org.reader.low.booknbook.controller.response.ComboResponse;
import org.reader.low.booknbook.controller.response.GeneroComboResponse;
import org.reader.low.booknbook.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/combo")
public class ComboControllerImpl implements ComboController {

    @Autowired
    private ComboService comboService;

    @Override
    public ComboResponse comboSaga(Long idAutor) {
        return comboService.comboSagas(idAutor);
    }

    @Override
    public ComboResponse comboAutores(String filter) {
        return comboService.comboAutores(filter);
    }

    @Override
    public GeneroComboResponse comboGenero() {
        return comboService.comboGenero();
    }

    @Override
    public ComboResponse comboDenunciaComentario() {
        return comboService.comboEstadoDenuncia(false);
    }

    @Override
    public ComboResponse comboDenunciaGrupo() {
        return comboService.comboEstadoDenuncia(true);
    }

    @Override
    public ComboResponse comboDenunciaMotivo() {
        return comboService.comboMotivoDenuncia();
    }
}
