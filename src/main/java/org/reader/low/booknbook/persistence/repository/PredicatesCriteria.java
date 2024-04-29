package org.reader.low.booknbook.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Grupo;
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
}
