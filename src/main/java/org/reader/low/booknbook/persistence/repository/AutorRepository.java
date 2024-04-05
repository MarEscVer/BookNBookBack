package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
