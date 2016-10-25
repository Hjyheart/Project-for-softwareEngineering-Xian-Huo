package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String home(ModelMap map, HttpServletRequest request){
        map.addAttribute("username", "Charon");
        map.addAttribute("sex", "男");
        map.addAttribute("studentId", "1452822");

        return "home";
    }


}
