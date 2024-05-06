package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    @Query("SELECT distinct(estado) FROM Denuncia")
    List<String> findAllEstadosDistinct();
}
