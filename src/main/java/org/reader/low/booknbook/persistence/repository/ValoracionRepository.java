package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.reader.low.booknbook.model.bnb.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, IdValoracion> {
}
