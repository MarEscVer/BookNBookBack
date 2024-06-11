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

/**
 * The type Predicates criteria.
 */
@Repository
@Slf4j
public class PredicatesCriteria {

    @Autowired
    EntityManager manager;

    /**
     * Search group list.
     *
     * @param filter the filter
     * @return the list
     */
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

    /**
     * Search autor list.
     *
     * @param filter the filter
     * @return the list
     */
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


    /**
     * Libros mas leidos list.
     *
     * @param genero    the genero
     * @param pageIndex the page index
     * @param limit     the limit
     * @return the list
     */
    public List<Valoracion> librosMasLeidos(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        Root<Valoracion> root = cq.from(Valoracion.class);
        Join<Valoracion, Genero> valoracionGeneroJoin = root.join("libro").join("genero", JoinType.LEFT);
        Join<Valoracion, Genero> valoracionTipoJoin = root.join("libro").join("tipo", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(genero)){
            predicates.add(cb.or(cb.equal(valoracionGeneroJoin.get("nombre"), genero),
                    cb.equal(valoracionTipoJoin.get("nombre"), genero)));
        }
        predicates.add(cb.equal(root.get("estado"), "LEIDO"));
        cq.where(predicates.toArray((Predicate[]::new)))
                .orderBy(cb.desc(cb.count(root)))
                .groupBy(root.get("id").get("idLibro"));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    /**
     * Libros recomendados list.
     *
     * @param genero    the genero
     * @param pageIndex the page index
     * @param limit     the limit
     * @return the list
     */
    public List<Valoracion> librosRecomendados(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        Root<Valoracion> root = cq.from(Valoracion.class);
        Join<Valoracion, Genero> valoracionGeneroJoin = root.join("libro").join("genero", JoinType.LEFT);
        Join<Valoracion, Genero> valoracionTipoJoin = root.join("libro").join("tipo", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(genero)){
            predicates.add(cb.or(cb.equal(valoracionGeneroJoin.get("nombre"), genero),
                    cb.equal(valoracionTipoJoin.get("nombre"), genero)));
        }
        Expression mediaCalificacionPersonal = cb.avg(root.get("calificacionPersonal"));
        cq.where(predicates.toArray((Predicate[]::new)))
             .orderBy(cb.desc(mediaCalificacionPersonal))
                .groupBy(root.get("id").get("idLibro"));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    /**
     * Libros novedades list.
     *
     * @param genero    the genero
     * @param pageIndex the page index
     * @param limit     the limit
     * @return the list
     */
    public List<Libro> librosNovedades(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Libro> cq = cb.createQuery(Libro.class);
        Root<Libro> root = cq.from(Libro.class);
        Join<Libro, Genero> libroGeneroJoin = root.join("genero", JoinType.LEFT);
        Join<Libro, Genero> libroTipoJoin = root.join("tipo", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(genero)){
            predicates.add(cb.or(cb.equal(libroGeneroJoin.get("nombre"), genero),
                    cb.equal(libroTipoJoin.get("nombre"), genero)));
        }
        Expression<Date> fechaPublicacion = root.get("fechaPublicacion");
        cq.where(predicates.toArray((Predicate[]::new)))
                .orderBy(cb.desc(fechaPublicacion));
        return manager.createQuery(cq)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    /**
     * Usuarios activos list.
     *
     * @param username  the username
     * @param pageIndex the page index
     * @param limit     the limit
     * @return the list
     */
    public List<Usuario> usuariosActivos(String username, Integer pageIndex, Integer limit){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(username)){
            predicates.add(cb.or(cb.like(root.get("nombreUsuario").as(String.class), "%" + username + "%"),
                    cb.like(root.get("nombre").as(String.class), "%" + username + "%")
                    ));
        }
        predicates.add(cb.equal(root.get("estado").as(Boolean.class), true));
        cq.where(predicates.toArray((Predicate[]::new)));
        return manager.createQuery(cq)
                //.setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }
}
