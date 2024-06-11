package org.reader.low.booknbook.model.bnb.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * The type Id paginas libro.
 */
@Embeddable
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class IdPaginasLibro implements Serializable {

    /**
     * The Id libro.
     */
    @Column(name = "id_libro")
    private Long idLibro;

    /**
     * The Id usuario.
     */
    @Column(name = "id_usuario")
    private Long idUsuario;

    /**
     * The Fecha.
     */
    @Column
    private Date fecha;
}
