package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
