package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
