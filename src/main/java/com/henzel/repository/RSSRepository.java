package com.henzel.repository;

import java.util.List;

import javax.persistencehenzel.*;

import org.springframework.stereotype.Component;

import com.henzel.model.RSS;
import com.henzel.repository.intf.RepositoryIntf;

@Component
public class RSSRepository implements RepositoryIntf<RSS> {

    @PersistenceContext
    private EntityManager em;

    public RSS save(RSS rss) {
        return em.merge(rss);
    }

    public void remove(RSS rss) {
        em.remove(rss);
    }

    public RSS getById(long id) {
        return em.find(RSS.class, id);
    }

    public List<RSS> getByQuery(String queryString) {
        TypedQuery<RSS> query = em.createQuery(queryString, RSS.class);
        return query.getResultList();
    }

    public RSS getObjectByQuery(String queryString) {
        List<RSS> result = getByQuery(queryString);
        return result.isEmpty() ? null : result.get(0);
    }
}