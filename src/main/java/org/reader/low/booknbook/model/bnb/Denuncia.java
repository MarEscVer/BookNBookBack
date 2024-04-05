package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "denuncia")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Denuncia {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motivo", nullable=false)
    private String motivo;

    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @Column(name = "fecha", nullable=false)
    private Date fecha;

    @OneToMany(mappedBy = "denuncia")
    private List<ComentarioGrupo> comentarioGrupo;

    @OneToMany(mappedBy = "denuncia")
    private List<Valoracion> valoracion;

}
