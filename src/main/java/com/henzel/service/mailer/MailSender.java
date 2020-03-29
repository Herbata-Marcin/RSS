package com.henzel.service.mailer;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    @Autowired
    private MailConfiguration mailConfiguration;

    private static final String SUBJECT = "New RSS";

    public void sendMail(String address, String msg) {
        try {
            MimeMessage message = new MimeMessage(mailConfiguration.getMailSession());
            Multipart multipart = new MimeMultipart("alternative");
            BodyPart part = new MimeBodyPart();
            part.setContent(msg, "text/html");
            multipart.addBodyPart(part);
            message.setFrom(new InternetAddress(mailConfiguration.getUsername()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
            message.setSubject(SUBJECT);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}