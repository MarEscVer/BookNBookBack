package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "autor")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Autor {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pseudonimo", nullable=false)
    private String pseudonimo;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "fotoAutor")
    private Blob fotoAutor;

    @Column(name = "biografia")
    private String biografia;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libro;
}
