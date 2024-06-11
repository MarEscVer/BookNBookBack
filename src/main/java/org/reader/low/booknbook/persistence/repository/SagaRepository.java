package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Saga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Saga repository.
 */
@Repository
public interface SagaRepository extends JpaRepository<Saga, Long> {
}
