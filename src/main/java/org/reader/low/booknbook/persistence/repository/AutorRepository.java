package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByPseudonimo(String pseudonimo);
}
