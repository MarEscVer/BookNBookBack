package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Genero;
import org.reader.low.booknbook.model.bnb.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
