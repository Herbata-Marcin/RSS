package com.henzel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henzel.model.*;
import com.henzel.repository.RSSRepository;
import com.henzel.service.mailer.MailValidator;
import com.henzel.service.rss.URLValidator;

@Service
public class RSSService {

    @Autowired
    private RSSRepository repository;

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MailValidator mailValidator;
    
    @Autowired
    private URLValidator urlValidator;

    @Transactional
    public RSS saveData(String url, String mail) {
        String myMail = mail.trim().toLowerCase();
        String myUrl = url.trim();
        if (!mailValidator.isValidate(myMail) || !urlValidator.isValidate(myUrl))
            return null;
        
        Account account = accountService.getAccount();
        if (account == null)
            account = accountService.saveAccount(myMail);

        if (!account.getMail().equals(myMail) || isRSSForAccountExist(myUrl, account))
            return null;

        return saveRSS(myUrl, account);
    }

    private boolean isRSSForAccountExist(String url, Account account) {
        RSS rss = repository.getObjectByQuery("FROM RSS WHERE url='" + url + "' AND account=" + account.getId());
        return rss == null ? false : true;
    }

    private RSS saveRSS(String url, Account account) {
        RSS rss = new RSS();
        rss.setUrl(url);
        rss.setAccount(account);
        return repository.save(rss);
    }

    @Transactional
    public void removeRSS(int id) {
        RSS rss = repository.getById(id);
        if (rss != null)
            repository.remove(rss);
    }
}