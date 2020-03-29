package com.henzel.service.mailer;

import java.util.regex.*;

import org.springframework.stereotype.Service;

@Service
public class MailValidator {

    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`" +
                                        "{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private Pattern pattern;
    private Matcher matcher;

    public boolean isValidate(String mail) {
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}