package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdPaginasLibro implements Serializable {

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column
    private Date fecha;
}
