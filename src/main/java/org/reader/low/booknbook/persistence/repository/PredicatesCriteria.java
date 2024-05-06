package org.reader.low.booknbook.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.model.bnb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class PredicatesCriteria {

    @Autowired
    private EntityManager manager;

    public List<Grupo> searchGroup(String filter) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Grupo> cq = cb.createQuery(Grupo.class);
        Root<Grupo> itemRoot = cq.from(Grupo.class);
        if(StringUtils.hasText(filter)) {
            Predicate predicateIdSearch = cb.like(itemRoot.get("id").as(String.class), "%" + filter + "%");
            Predicate predicateDescripcionSearch = cb.like(itemRoot.get("descripcion").as(String.class), "%" + filter + "%");
            Predicate predicateNombreSearch = cb.like(itemRoot.get("nombre").as(String.class), "%" + filter + "%");
            Predicate idorDescription = cb.or(predicateIdSearch, predicateDescripcionSearch);
            Predicate idOrDescrptionOrNombre = cb.or(idorDescription, predicateNombreSearch);
            cq.where(idOrDescrptionOrNombre);
        }
        return manager.createQuery(cq).getResultList();
    }

    public List<Autor> searchAutor(String filter) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Autor> cq = cb.createQuery(Autor.class);
        Root<Autor> itemRoot = cq.from(Autor.class);
        if(StringUtils.hasText(filter)){
            Predicate predicatePseudonimo = cb.like(itemRoot.get("pseudonimo").as(String.class), "%"+filter+"%");
            cq.where(predicatePseudonimo);
        }
        return manager.createQuery(cq).getResultList();
    }


    public List<Valoracion> librosMasLeidos(Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        Root<Valoracion> root = cq.from(Valoracion.class);
        root.join("libro");
        Predicate estadoLeido = cb.equal(root.get("estado"), "LEIDO");
        cq.where(estadoLeido)
                .groupBy(root.get("id").get("idLibro"))
                .orderBy(cb.desc(cb.count(root)));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    public List<Valoracion> librosRecomendados(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        Root<Valoracion> root = cq.from(Valoracion.class);
        Join<Valoracion, Genero> valoracionGeneroJoin = root.join("libro").join("genero");
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(genero)){
            predicates.add(cb.equal(valoracionGeneroJoin.get("nombre"), genero));
        }
        Expression mediaCalificacionPersonal = cb.avg(root.get("calificacionPersonal"));
        cq.where(predicates.toArray((Predicate[]::new)))
             .orderBy(cb.desc(mediaCalificacionPersonal))
                .groupBy(root.get("id").get("idLibro"));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    public List librosNovedades2(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        //CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        String sql = "select l.id, l.descripcion, l.fecha_publicacion, l.foto_libro, l.nombre, l.pag_total, l.id_autor, l.id_genero, l.id_saga, l.id_tipo from libro as l join genero as g on l.id_genero = g.id " +
                "join genero as g on l.id_genero = g.id ";
        if(StringUtils.hasText(genero)){
            sql += "where g.nombre = :genero ";
        }
        sql += "order by fecha_publicacion desc";
        return manager.createQuery(sql).setParameter("genero", genero)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    public List<Libro> librosNovedades(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Libro> cq = cb.createQuery(Libro.class);
        Root<Libro> root = cq.from(Libro.class);
        Join<Libro, Genero> libroGeneroJoin = root.join("genero");
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(genero)){
            predicates.add(cb.equal(libroGeneroJoin.get("nombre"), genero));
        }
        Expression<Date> fechaPublicacion = root.get("fechaPublicacion");
        cq.where(predicates.toArray((Predicate[]::new)))
                .orderBy(cb.desc(fechaPublicacion));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }
}
