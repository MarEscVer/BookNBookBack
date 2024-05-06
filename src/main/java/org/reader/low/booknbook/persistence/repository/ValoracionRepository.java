package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Valoracion;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, IdValoracion> {

    List<Valoracion> findByDenunciaNotNullOrderByFechaComentarioDesc();

    Long countByEstado(String estado);

    Long countByComentarioNotNull();

}
