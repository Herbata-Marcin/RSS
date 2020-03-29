package com.henzel.service.rss;

import java.util.regex.*;

import org.springframework.stereotype.Service;

@Service
public class URLValidator {

    private static final String REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\." + 
                                        "[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    private Pattern pattern;
    private Matcher matcher;

    public boolean isValidate(String url) {
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(url);
        return matcher.matches();
    }
}