package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    // 重定向到home
    public String charon(){
        return "redirect:/home";
    }

    @RequestMapping("home")
    // 首页
    public String home(ModelMap map){
        map.addAttribute("name", "Home");
        return "home";
    }
}
