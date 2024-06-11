package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Saga.
 */
@Entity
@Table(name = "saga")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Saga implements Serializable {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    Long id;

    /**
     * The Nombre.
     */
    @Column(name = "nombre", nullable=false)
    String nombre;

    /**
     * The Libro.
     */
    @OneToMany(mappedBy = "saga", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    List<Libro> libro = new ArrayList<>();
}
