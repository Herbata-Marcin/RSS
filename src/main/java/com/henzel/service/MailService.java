package com.henzel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henzel.service.mailer.*;
import com.henzel.service.rss.RSSReader;

@Service
public class MailService {

    @Autowired
    private MailValidator mailValidator;

    @Autowired
    private RSSReader rssReader;

    @Autowired
    private MailSender mailSender;

    public String sendMail(String mail) {
        String myMail = mail.trim().toLowerCase();
        if (!mailValidator.isValidate(myMail))
            return null;

        String message = rssReader.getMessage(myMail);
        if (message != null)
            mailSender.sendMail(myMail, message);
        
        return message;
    }
}