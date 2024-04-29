package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Grupo;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.model.bnb.UsuarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, IdUsuarioGrupo> {

    List<UsuarioGrupo> findByGrupo(Grupo grupo);

    List<UsuarioGrupo> findAllByUsuario(Usuario usuario);
}
