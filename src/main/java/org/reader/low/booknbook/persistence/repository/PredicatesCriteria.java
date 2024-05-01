package org.reader.low.booknbook.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Grupo;
import org.reader.low.booknbook.model.bnb.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
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

    public List librosMasLeidos2(Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        //CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        String sql = "SELECT l.id, l.descripcion, l.fecha_publicacion, l.foto_libro, l.nombre, l.pag_total, l.id_autor, l.id_genero, l.id_saga, l.id_tipo " +
                "FROM valoracion as v join libro as l ON v.id_libro=l.id " +
                "where v.estado='LEIDO' " +
                "group by v.id_libro " +
                "order by count(*) desc";
        return manager.createQuery(sql)
                //.setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    public List librosMasLeidos(Integer pageIndex, Integer limit) {
        String sql = "SELECT `l.id`, `l.descripcion`, `l.fecha_publicacion`, `l.foto_libro`, `l.nombre`, `l.pag_total`, `l.id_autor`, `l.id_genero`, `l.id_saga`, `l.id_tipo` " +
                "FROM `valoracion` as v join `libro` as l ON v.`id_libro`=l.`id` " +
                "where v.`estado`='LEIDO' " +
                "group by v.`id_libro` " +
                "order by count(l.`id`) desc";
        TypedQuery<Libro> query = manager.createQuery(sql, Libro.class);
        return query.setFirstResult(pageIndex).setMaxResults(limit).getResultList();
    }

    public List librosRecomendados2(String genero, Integer pageIndex, Integer limit) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        //CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        String sql = "select l.id, l.descripcion, l.fecha_publicacion, l.foto_libro, l.nombre, l.pag_total, l.id_autor, l.id_genero, l.id_saga, l.id_tipo from valoracion as v join libro as l on v.id_libro = l.id " +
                "join genero as g on l.id_genero = g.id ";
                if(StringUtils.hasText(genero)){
                    sql += "where g.nombre = :genero ";
                }
                sql += "order by v.calificacion_personal desc";
        return manager.createQuery(sql).setParameter("genero", genero)
                .setFirstResult(pageIndex).setMaxResults(limit)
                .getResultList();
    }

    public List librosRecomendados(String genero, Integer pageIndex, Integer limit) {
        String sql = "select l.id, l.descripcion, l.fecha_publicacion, l.foto_libro, l.nombre, l.pag_total, l.id_autor, l.id_genero, l.id_saga, l.id_tipo from valoracion as v join libro as l on v.id_libro = l.id " +
                "join genero as g on l.id_genero = g.id ";
        if(StringUtils.hasText(genero)){
            sql += "where g.nombre = :genero ";
        }
        sql += "order by v.calificacion_personal desc";

        TypedQuery<Libro> query = manager.createQuery(sql, Libro.class);
        return query.setParameter("genero", genero).setFirstResult(pageIndex).setMaxResults(limit).getResultList();
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

    public List librosNovedades(String genero, Integer pageIndex, Integer limit) {
        String sql = "select l.id, l.descripcion, l.fecha_publicacion, l.foto_libro, l.nombre, l.pag_total, l.id_autor, l.id_genero, l.id_saga, l.id_tipo from libro as l join genero as g on l.id_genero = g.id " +
                "join genero as g on l.id_genero = g.id ";
        if(StringUtils.hasText(genero)){
            sql += "where g.nombre = :genero ";
        }
        sql += "order by fecha_publicacion desc";
        TypedQuery<Libro> query = manager.createQuery(sql, Libro.class);
        return query.setParameter("genero", genero).setFirstResult(pageIndex).setMaxResults(limit).getResultList();
    }
}
