package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;

import java.io.Serializable;

@Entity
@Table(name = "usuario_grupo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UsuarioGrupo implements Serializable {

    @EmbeddedId
    private IdUsuarioGrupo id = new IdUsuarioGrupo();

    @Column(name = "rol", nullable=false, columnDefinition = "varchar(25) default 'NORMAL'")
    private String rol;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @MapsId("idGrupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
