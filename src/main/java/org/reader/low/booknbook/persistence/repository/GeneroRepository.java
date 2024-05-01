package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    List<Genero> findAllByTipo(String tipo);
}
