package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.controller.object.ModerateComments;

public class FiltersMapping {

    public static boolean filtroModerateComments(String filter, ModerateComments comentario) {
        return comentario.getIdValoracionLibro().toString().contains(filter) ||
                comentario.getIdValoracionUsuario().toString().contains(filter) ||
                comentario.getNombreUsuario().contains(filter) ||
                comentario.getComentario().contains(filter) ||
                comentario.getMotivo().contains(filter);
    }
}
