package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Valoracion;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Valoracion repository.
 */
@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, IdValoracion> {

    /**
     * Find by denuncia not null order by fecha comentario desc list.
     *
     * @return the list
     */
    List<Valoracion> findByDenunciaNotNullOrderByFechaComentarioDesc();

    /**
     * Count by estado long.
     *
     * @param estado the estado
     * @return the long
     */
    Long countByEstado(String estado);

    /**
     * Count by comentario not null long.
     *
     * @return the long
     */
    Long countByComentarioNotNull();

}
