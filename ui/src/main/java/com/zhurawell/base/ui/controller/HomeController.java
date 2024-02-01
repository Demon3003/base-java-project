package com.zhurawell.base.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model m)
    {
        m.addAttribute("msg", "Welcome");
        return "home";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

}
