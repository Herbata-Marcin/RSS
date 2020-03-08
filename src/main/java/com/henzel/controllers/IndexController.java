package com.henzel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "index.html", "/" })
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request, ModelMap model) {
        model.addAttribute("account", request.getParameter("account"));
        return "index";
    }
}