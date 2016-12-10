package com.example.controller.web;

import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentService studentService = new StudentService();

    @RequestMapping("")
    // 登录页
    public String loginView(ModelMap map){
        map.addAttribute("name", "LoginView");
        return "web/login";
    }

    @RequestMapping(value = "/vertify", method = RequestMethod.POST)
    @ResponseBody
    // 处理登录
    public Integer dealLogin(HttpServletRequest request, ModelMap map){

        String id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();

        Integer flag = this.studentService.login(id,password);

        switch (flag){
            case 1:
                request.getSession().setAttribute("user_id", id);
                request.getSession().setAttribute("user_password", password);
                break;
            case 0:
                break;
            case -1:
                break;
        }
        return flag;
    }

    @RequestMapping("/register")
    // 跳转到注册页
    public String turnToRegister(){
        return "redirect:/register";
    }

    @RequestMapping("/if")
    @ResponseBody
    // 是否登录
    public boolean loginIf(HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null){
            return false;
        }else{
            return true;
        }

    }
}
