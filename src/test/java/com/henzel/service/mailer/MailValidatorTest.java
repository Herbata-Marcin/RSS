package com.henzel.service.mailer;

import java.util.regex.*;
import org.junit.*;

public class MailValidatorTest {

    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`" +
                                        "{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private Pattern pattern;
    private Matcher matcher;

    @Test
    public void isValidate() {
        pattern = Pattern.compile(REGEX);
        String mail = "mail@poczta.com";
        matcher = pattern.matcher(mail);
        Assert.assertTrue("mail is correct", matcher.matches());
        mail = "mail@";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is not valid - without full domain", matcher.matches());
        mail = "mail@poczta";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is not valid - without domain", matcher.matches());
        mail = "mail@poczta.";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is not valid - without domain", matcher.matches());
        mail = "@poczta.com";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is not valid - without username", matcher.matches());
        mail = "mailpoczta.com";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is not valid - without @", matcher.matches());
        mail = "MaIL@poczTA.CoM";
        matcher = pattern.matcher(mail);
        Assert.assertTrue("mail is correct", matcher.matches());
        mail = "";
        matcher = pattern.matcher(mail);
        Assert.assertFalse("mail is empty", matcher.matches());
    }
}