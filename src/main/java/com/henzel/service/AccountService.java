package com.henzel.service;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henzel.model.*;
import com.henzel.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account getAccount(String mail) {
        try {
            return repository.getByMail(mail);
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Account getAccount() {
        return repository.getObjectByQuery("FROM Account");
    }

    Account saveAccount(String mail) {
        Account account = new Account();
        account.setMail(mail);
        return repository.save(account);
    }
}