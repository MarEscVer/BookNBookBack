package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pseudonimo", nullable=false)
    private String pseudonimo;

    @Column(name = "localidad")
    private String localidad;

    @Lob
    @Column(name = "fotoAutor", columnDefinition = "LONGBLOB")
    private byte[] fotoAutor;

    @Column(name = "biografia")
    private String biografia;

    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    public void addLibro(Libro libro){
        if(libro != null){
            libro.setAutor(this);
            libros.add(libro);
        }
    }
}
