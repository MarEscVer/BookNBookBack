package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.*;
import org.reader.low.booknbook.model.bnb.Grupo;
import org.reader.low.booknbook.model.bnb.Libro;
import org.reader.low.booknbook.model.bnb.Usuario;

import java.io.Serializable;

@Embeddable
public class IdUsuarioGrupo implements Serializable {

    @Column(name = "id_grupo")
    private Long idGrupo;

    @Column(name = "id_usuario")
    private Long idUsuario;

}
