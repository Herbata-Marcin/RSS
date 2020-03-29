package com.henzel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.mahenzellik.model.Account;
import com.henzel.model.RSS;
import com.henzel.service.*;
import com.henzel.status.Status;

@Controller
@RequestMapping("index")
public class IndexController {

    private static final String AUTHOR = "Marcin Henzel";

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private RSSService rssService;
    
    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model) {
        Account account = accountService.getAccount();
        if (account != null) {
            model.addAttribute("mail", account.getMail());
            model.addAttribute("rssList", account.getRss());
        }
        model.addAttribute("author", AUTHOR);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/saveAjax", method = RequestMethod.POST)
    public String saveAjax(@RequestParam(value = "url") String url, @RequestParam(value = "mail") String mail) {
        RSS rss = rssService.saveData(url, mail);
        return rss == null ? Status.ERROR.getMessage() : rss.getId().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/sendAjax", method = RequestMethod.POST)
    public String sendAjax(@RequestParam(value = "mail") String mail) {
        String message = mailService.sendMail(mail);
        return message == null ? Status.ERROR.getMessage() : message;
    }

    @RequestMapping(value = "/removeAjax", method = RequestMethod.POST)
    public String removeAjax(ModelMap model, @RequestParam(value = "id") int id) {
        rssService.removeRSS(id);
        return get(model);
    }
}