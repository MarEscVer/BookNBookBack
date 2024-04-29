package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.response.ComboResponse;

public interface ComboService {
    ComboResponse comboSagas(Long idAutor);

    ComboResponse comboAutores(String filter);
}
