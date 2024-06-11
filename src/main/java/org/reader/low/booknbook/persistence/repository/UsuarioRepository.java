package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Usuario repository.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Find by nombre usuario optional.
     *
     * @param nombreUsuario the nombre usuario
     * @return the optional
     */
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
