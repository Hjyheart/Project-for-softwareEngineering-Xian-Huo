package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping("")
    // 注册页
    public String register(ModelMap map){
        map.addAttribute("name", "RegisterView");
        return "home";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    // 处理注册
    public @ResponseBody String dealRegister(HttpServletRequest request){
        String username = request.getParameter("username").trim();
        System.out.println(username);

        return "redirect:home";
    }
}
