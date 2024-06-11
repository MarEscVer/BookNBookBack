package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.controller.object.ModerateComments;

/**
 * The type Filters mapping.
 */
public class FiltersMapping {

    /**
     * Filtro moderate comments boolean.
     *
     * @param filter     the filter
     * @param comentario the comentario
     * @return the boolean
     */
    public static boolean filtroModerateComments(String filter, ModerateComments comentario) {
        return comentario.getIdValoracionLibro().toString().contains(filter) ||
                comentario.getIdValoracionUsuario().toString().contains(filter) ||
                comentario.getNombreUsuario().contains(filter) ||
                comentario.getComentario().contains(filter) ||
                comentario.getMotivo().contains(filter);
    }
}
