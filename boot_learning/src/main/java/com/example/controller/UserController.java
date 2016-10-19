package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hongjiayong on 2016/10/13.
 */
@Controller
public class UserController {
    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("host", "http://www.baidu.com");
        return "home";
    }
}
