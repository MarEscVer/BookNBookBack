package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Denuncia repository.
 */
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    /**
     * Find all estados distinct list.
     *
     * @return the list
     */
    @Query("SELECT distinct(estado) FROM Denuncia")
    List<String> findAllEstadosDistinct();
}
