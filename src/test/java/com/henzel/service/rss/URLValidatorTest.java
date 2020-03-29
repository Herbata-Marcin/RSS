package com.henzel.service.rss;

import java.util.regex.*;
import org.junit.*;

public class URLValidatorTest {

    private static final String REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\." +
                                        "[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    private Pattern pattern;
    private Matcher matcher;

    @Test
    public void isValidate() {
        pattern = Pattern.compile(REGEX);
        String url = "https://www.example.com";
        matcher = pattern.matcher(url);
        Assert.assertTrue("url is correct", matcher.matches());
        url = "http://www.example.com";
        matcher = pattern.matcher(url);
        Assert.assertTrue("url is correct", matcher.matches());
        url = "http://example.com";
        matcher = pattern.matcher(url);
        Assert.assertTrue("url is correct", matcher.matches());
        url = "www.example.com";
        matcher = pattern.matcher(url);
        Assert.assertFalse("url is not valid", matcher.matches());
        url = "http://www.example";
        matcher = pattern.matcher(url);
        Assert.assertFalse("url is not valid", matcher.matches());
        url = "http:www.example.com";
        matcher = pattern.matcher(url);
        Assert.assertFalse("url is not valid", matcher.matches());
        url = "";
        matcher = pattern.matcher(url);
        Assert.assertFalse("url is empty", matcher.matches());
    }
}