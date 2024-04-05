package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.*;
import org.reader.low.booknbook.model.bnb.Libro;
import org.reader.low.booknbook.model.bnb.Saga;
import org.reader.low.booknbook.model.bnb.Usuario;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class IdPaginasLibro implements Serializable {

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "id_usuario")
    private Long idUsuario;

    private Date fecha;
}
