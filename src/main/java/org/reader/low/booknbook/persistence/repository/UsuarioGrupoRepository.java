package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Grupo;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.model.bnb.UsuarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Usuario grupo repository.
 */
public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, IdUsuarioGrupo> {

    /**
     * Find by grupo list.
     *
     * @param grupo the grupo
     * @return the list
     */
    List<UsuarioGrupo> findByGrupo(Grupo grupo);

    /**
     * Find all by usuario list.
     *
     * @param usuario the usuario
     * @return the list
     */
    List<UsuarioGrupo> findAllByUsuario(Usuario usuario);
}
