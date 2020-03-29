package com.henzel.service.mailer;

import java.io.*;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.*;

import org.springframework.stereotype.Service;

@Service
public class MailConfiguration {
    
    private Properties PROPERTIES = new Properties();

    private Session mailSession;

    private String USERNAME;
    private static String HOST;
    private static String PASSWORD;
    private static int PORT;

    @PostConstruct
    public void loadSettings() {
        loadMailerPropertiesFile();
        HOST = PROPERTIES.getProperty("mailer.host");
        USERNAME = PROPERTIES.getProperty("mailer.username");
        PASSWORD = PROPERTIES.getProperty("mailer.password");
        PORT = Integer.valueOf(PROPERTIES.getProperty("mailer.port"));
        setMailerSession();
    }

    private void loadMailerPropertiesFile() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("mailer.properties");
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMailerSession() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", "true");
        mailSession = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getUsername(), getPassword());
            }
        });
    }

    String getUsername() {
        return USERNAME;
    }

    static String getPassword() {
        return PASSWORD;
    }

    Session getMailSession() {
        return mailSession;
    }
}