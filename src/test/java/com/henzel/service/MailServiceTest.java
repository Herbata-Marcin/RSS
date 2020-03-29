package com.henzel.service;

import org.junit.*;

public class MailServiceTest {

    @Test
    public void checkMailString() {
        String mail1 = "mail@poczta.com";
        String mail2 = "MAil@poczta.com ";
        Assert.assertEquals("mails must be the same", mail1, mail2.trim().toLowerCase());
        Assert.assertNotEquals("mails must be different", mail1, mail2);
    }
}