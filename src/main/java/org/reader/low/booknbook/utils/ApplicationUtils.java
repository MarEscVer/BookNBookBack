package org.reader.low.booknbook.utils;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class ApplicationUtils {

    public static boolean verifyObjectInstance(Object objeto){
        return objeto != null;
    }

    public static List filteringListPage(List lista, Pageable pageable) {
        long end = Math.min((pageable.getOffset() + pageable.getPageSize()), lista.size());
        return lista.subList((int)pageable.getOffset(), Math.toIntExact(end));
    }
}
