package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Denuncia.
 */
@Entity
@Table(name = "denuncia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Denuncia implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Motivo.
     */
    @Column(name = "motivo", nullable=false)
    private String motivo;

    /**
     * The Texto.
     */
    @Lob
    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    /**
     * The Fecha.
     */
    @Column(name = "fecha", nullable=false)
    Date fecha;

    /**
     * The Estado.
     */
    @Column(name = "estado")
    private String estado;

    /**
     * The Comentario grupo.
     */
    @OneToMany(mappedBy = "denuncia", cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

    /**
     * The Valoracion.
     */
    @OneToMany(mappedBy = "denuncia", cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

}
