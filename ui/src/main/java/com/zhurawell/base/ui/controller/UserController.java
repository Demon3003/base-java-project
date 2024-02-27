package com.zhurawell.base.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/userDetails")
    public String getUserDetails(Model m) {
        m.addAttribute("msg", "Welcome Dima");
        return "userDetails";
    }

}
