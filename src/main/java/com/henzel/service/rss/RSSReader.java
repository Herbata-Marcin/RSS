package com.henzel.service.rss;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henzel.model.*;
import com.henzel.service.AccountService;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.*;

@Service
public class RSSReader {

    @Autowired
    private AccountService accountService;

    public String getMessage(String mail) {
        Account account = accountService.getAccount(mail);
        if (account == null || account.getRss().isEmpty())
            return null;

        return getMessage(account);
    }

    private static String getMessage(Account account) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RSS rss : account.getRss()) {
            try {
                buildString(stringBuilder, getFeed(rss));
            } catch (FeedException | IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }

        return stringBuilder.length() < 1 ? null : stringBuilder.toString();
    }

    private static SyndFeed getFeed(RSS rss) throws FeedException, IOException {
        URL url = new URL(rss.getUrl());
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));
        return feed;
    }

    private static void buildString(StringBuilder stringBuilder, SyndFeed feed) {
        List<SyndEntry> entries = feed.getEntries();
        entries.forEach(entry -> {
            stringBuilder
                .append("<b>")
                .append(entry.getTitle())
                .append("</b><br>")
                .append(entry.getDescription() != null ? entry.getDescription().getValue() : "")
                .append("<br>")
                .append("<a href='")
                .append(entry.getLink())
                .append("' target='_blank'>")
                .append(entry.getLink())
                .append("</a><br>------<br>");
        });
    }
}