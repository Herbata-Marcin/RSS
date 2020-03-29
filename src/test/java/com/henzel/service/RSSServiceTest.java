package com.henzel.service;

import org.junit.*;
import com.henzel.model.*;

public class RSSServiceTest {

    @Test
    public void getURL() {
        String url = "http://onet.pl";
        RSS rss = new RSS();
        rss.setUrl(url);
        Assert.assertEquals("urls must be the same", url, rss.getUrl());
        Assert.assertNotEquals("urls must be different", rss.getUrl(), "https://onet.com");
    }

    @Test
    public void getAccount() {
        Account account = new Account();
        RSS rss = new RSS();
        rss.setAccount(account);
        Account newAccount = new Account();
        Assert.assertEquals("accounts must be the same", account, rss.getAccount());
        Assert.assertNotEquals("accounts must be different", rss.getAccount(), newAccount);
    }
}