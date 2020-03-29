package com.henzel.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.henzel.model.Account;
import com.henzel.repository.intf.RepositoryIntf;

@Component
public class AccountRepository implements RepositoryIntf<Account> {

    @PersistenceContext
    private EntityManager em;

    public Account save(Account account) {
        return em.merge(account);
    }

    public void remove(Account account) {
        em.remove(account);
    }

    public Account getById(long id) {
        return em.find(Account.class, id);
    }

    public List<Account> getByQuery(String queryString) {
        TypedQuery<Account> query = em.createQuery(queryString, Account.class);
        return query.getResultList();
    }

    public Account getObjectByQuery(String queryString) {
        List<Account> result = getByQuery(queryString);
        return result.isEmpty() ? null : result.get(0);
    }

    public Account getByMail(String mail) {
        TypedQuery<Account> query = em.createQuery("FROM Account WHERE mail=:mail", Account.class);
        return query.setParameter("mail", mail).getSingleResult();
    }
}