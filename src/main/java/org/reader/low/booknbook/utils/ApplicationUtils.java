package org.reader.low.booknbook.utils;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The type Application utils.
 */
public class ApplicationUtils {

    /**
     * Verify object instance boolean.
     *
     * @param objeto the objeto
     * @return the boolean
     */
    public static boolean verifyObjectInstance(Object objeto){
        return objeto != null;
    }

    /**
     * Filtering list page list.
     *
     * @param lista    the lista
     * @param pageable the pageable
     * @return the list
     */
    public static List filteringListPage(List lista, Pageable pageable) {
        long end = Math.min((pageable.getOffset() + pageable.getPageSize()), lista.size());
        return lista.subList((int)pageable.getOffset(), Math.toIntExact(end));
    }

    /*public static Object isNull(Object objecto, Function<Object, Object> valor, Object valorAlternativo){
        return Objects.isNull(objecto) ? valorAlternativo : valor;
    }*/
}
