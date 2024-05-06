package org.reader.low.booknbook.persistence.repository;

import org.reader.low.booknbook.model.bnb.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long>, JpaRepository<Libro, Long> {

    /*@Modifying
    @Query("SELECT libro " +
            "FROM valoracion as v join libro as l ON v.id_libro=l.id " +
            "where v.estado='LEIDO' " +
            "group by v.id_libro " +
            "order by count(l.id) as cuenta desc")
    ArrayList<Libro> librosMasLeidos();


    @Modifying
    @Query("select libro from libro as l join genero as g on l.id_genero = g.id " +
            "join genero as g on l.id_genero = g.id where g.nombre = :genero order by fecha_publicacion desc")
    ArrayList<Libro> librosNovedades(@Param("genero") String genero);*/

}
