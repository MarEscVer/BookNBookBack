package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long>, JpaRepository<Libro, Long> {

}
