package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.response.ComboResponse;
import org.reader.low.booknbook.controller.response.GeneroComboResponse;

public interface ComboService {
    ComboResponse comboSagas(Long idAutor);

    ComboResponse comboAutores(String filter);

    GeneroComboResponse comboGenero();

    ComboResponse comboEstadoDenuncia(boolean comboComentarioGrupo);

    ComboResponse comboMotivoDenuncia();
}
