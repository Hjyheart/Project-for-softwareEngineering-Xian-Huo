package com.example.controller;

import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hongjiayong on 2016/10/21.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentService studentService = new StudentService();

    @RequestMapping("")
    // 登录页
    public String loginView(ModelMap map){
        map.addAttribute("name", "LoginView");
        return "loginView";
    }

    @RequestMapping(value = "/vertify", method = RequestMethod.POST)
    // 处理登录
    public String dealLogin(HttpServletRequest request, ModelMap map){

        String id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();


        if (studentService.findByMId(id) != null) {
            request.getSession().setAttribute("user_id", id);
            request.getSession().setAttribute("user_password", password);

            return "redirect:/home";

        }
        else return "redirect:/home";


    }

    @RequestMapping("/register")
    // 跳转到注册页
    public String turnToRegister(){
        return "redirect:/register";
    }
}
