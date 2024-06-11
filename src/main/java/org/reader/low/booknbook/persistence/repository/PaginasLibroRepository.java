package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;
import org.reader.low.booknbook.model.bnb.PaginasLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Paginas libro repository.
 */
@Repository
public interface PaginasLibroRepository extends JpaRepository<PaginasLibro, IdPaginasLibro> {
}
