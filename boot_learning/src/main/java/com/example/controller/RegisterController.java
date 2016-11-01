package com.example.controller;

import com.example.service.StudentService;
import com.example.service.repository.StudentRepository;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StudentService studentService = new StudentService();

    @RequestMapping("")
    // 注册页
    public String register(ModelMap map){
        map.addAttribute("name", "RegisterView");
        return "register";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    // 处理注册
    public @ResponseBody String dealRegister(HttpServletRequest request){
        String mId = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();
        String grade = request.getParameter("grade").trim();
        String major = request.getParameter("major").trim();
        String contact = request.getParameter("contact").trim();


        studentService.setPersonalInfo(mId,name,grade,major,contact,password);

        return "redirect:/home";
    }
}
