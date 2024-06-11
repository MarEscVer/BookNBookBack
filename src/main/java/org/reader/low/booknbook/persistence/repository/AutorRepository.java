package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Autor repository.
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    /**
     * Find by pseudonimo optional.
     *
     * @param pseudonimo the pseudonimo
     * @return the optional
     */
    Optional<Autor> findByPseudonimo(String pseudonimo);
}
