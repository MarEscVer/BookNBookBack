package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "denuncia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Denuncia implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motivo", nullable=false)
    private String motivo;

    @Lob
    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @Column(name = "fecha", nullable=false)
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "denuncia", cascade=CascadeType.ALL)
    private List<ComentarioGrupo> comentarioGrupo = new ArrayList<>();

    @OneToMany(mappedBy = "denuncia", cascade=CascadeType.ALL)
    private List<Valoracion> valoracion = new ArrayList<>();

}
