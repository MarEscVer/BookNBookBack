package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.id.IdSeguimiento;
import org.reader.low.booknbook.model.bnb.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, IdSeguimiento> {
}
