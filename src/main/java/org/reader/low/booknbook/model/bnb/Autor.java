package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Autor.
 */
@Entity
@Table(name = "autor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Autor implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Pseudonimo.
     */
    @Column(name = "pseudonimo", nullable=false)
    private String pseudonimo;

    /**
     * The Localidad.
     */
    @Column(name = "localidad")
    private String localidad;

    /**
     * The Foto autor.
     */
    @Lob
    @Column(name = "fotoAutor", columnDefinition = "LONGBLOB")
    private byte[] fotoAutor;

    /**
     * The Biografia.
     */
    @Column(name = "biografia")
    private String biografia;

    /**
     * The Libros.
     */
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    /**
     * Add libro.
     *
     * @param libro the libro
     */
    public void addLibro(Libro libro){
        if(libro != null){
            libro.setAutor(this);
            libros.add(libro);
        }
    }

    /**
     * Delete libro.
     *
     * @param libro the libro
     */
    public void deleteLibro(Libro libro) {
        if(libro != null && libro.getId() != null) {
            libros.remove(libro);
        }
    }

    /**
     * Update libro.
     *
     * @param libro the libro
     */
    public void updateLibro(Libro libro) {
        if(libro != null && libro.getId() != null) {
            libros.stream().forEach( libro1 -> {
                if(libro.getId() == libro1.getId()){
                    libro1 = libro;
                }});
        }
    }
}
