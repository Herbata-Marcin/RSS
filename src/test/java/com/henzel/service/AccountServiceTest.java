package com.henzel.service;

import org.junit.*;
import com.malik.model.Account;

public class AccountServiceTest {

    @Test
    public void getMail() {
        String mail = "adres@mailowy.com";
        Account account = new Account();
        account.setMail(mail);
        Assert.assertEquals("mails must be the same", mail, account.getMail());
    }
}