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

    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    public void addLibro(Libro libro){
        if(libro != null){
            libro.setAutor(this);
            libros.add(libro);
        }
    }

    public void deleteLibro(Libro libro) {
        if(libro != null && libro.getId() != null) {
            libros.remove(libro);
        }
    }

    public void updateLibro(Libro libro) {
        if(libro != null && libro.getId() != null) {
            libros.stream().forEach( libro1 -> {
                if(libro.getId() == libro1.getId()){
                    libro1 = libro;
                }});
        }
    }
}
