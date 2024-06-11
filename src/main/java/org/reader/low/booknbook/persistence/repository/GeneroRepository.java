package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Genero repository.
 */
@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    /**
     * Find all by tipo list.
     *
     * @param tipo the tipo
     * @return the list
     */
    List<Genero> findAllByTipo(String tipo);
}
