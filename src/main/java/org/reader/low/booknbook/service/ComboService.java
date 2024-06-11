package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.response.ComboResponse;
import org.reader.low.booknbook.controller.response.GeneroComboResponse;

/**
 * The interface Combo service.
 */
public interface ComboService {
    /**
     * Combo sagas combo response.
     *
     * @param idAutor the id autor
     * @return the combo response
     */
    ComboResponse comboSagas(Long idAutor);

    /**
     * Combo autores combo response.
     *
     * @param filter the filter
     * @return the combo response
     */
    ComboResponse comboAutores(String filter);

    /**
     * Combo genero genero combo response.
     *
     * @return the genero combo response
     */
    GeneroComboResponse comboGenero();

    /**
     * Combo estado denuncia combo response.
     *
     * @param comboComentarioGrupo the combo comentario grupo
     * @return the combo response
     */
    ComboResponse comboEstadoDenuncia(boolean comboComentarioGrupo);

    /**
     * Combo motivo denuncia combo response.
     *
     * @return the combo response
     */
    ComboResponse comboMotivoDenuncia();
}
