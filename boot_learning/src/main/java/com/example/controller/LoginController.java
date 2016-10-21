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
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("")
    // 登录页
    public String loginView(ModelMap map){
        map.addAttribute("name", "LoginView");
        return "home";
    }

    @RequestMapping(value = "/vertify", method = RequestMethod.POST)
    // 处理登录
    public @ResponseBody String dealLogin(HttpServletRequest request, ModelMap map){
        String username = request.getParameter("username").trim();
        System.out.println(username);

        return "redirect:/home";
    }

    @RequestMapping("/register")
    // 跳转到注册页
    public String turnToRegister(){
        return "redirect:/register";
    }
}
