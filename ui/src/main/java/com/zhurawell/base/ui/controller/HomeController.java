package com.zhurawell.base.ui.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Primary
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model m)
    {
        m.addAttribute("msg", "Welcome Dima");
        return "home";
    }

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/home";
    }

    @GetMapping({"/login", "/login.html"})
    public String login()
    {
        return "login";
    }

    @RequestMapping("/error")
    public String error(Locale locale) {
        return "redirect:/home";
    }

}
