package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "autor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pseudonimo", nullable=false)
    private String pseudonimo;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "fotoAutor")
    private byte[] fotoAutor;

    @Column(name = "biografia")
    private String biografia;

    @OneToMany(mappedBy = "autor", cascade=CascadeType.ALL)
    private List<Libro> libro;
}
