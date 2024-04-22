package org.reader.low.booknbook.model.bnb;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "saga")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Saga {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @OneToMany(mappedBy = "saga")
    private List<Libro> libro;
}
