package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
<<<<<<< HEAD

=======
import com.example.service.TeacherService;
>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.sockjs.transport.handler.JsonpPollingTransportHandler;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StudentService studentService = new StudentService();

    @Autowired
    private TeacherService teacherService = new TeacherService();

    @RequestMapping("")
    // 注册页
    public String register(ModelMap map){
        map.addAttribute("name", "RegisterView");
        return "registerStudent";
    }


    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    // 处理注册
<<<<<<< HEAD
    public @ResponseBody String dealRegister( HttpServletRequest request){

=======
    public @ResponseBody String dealRegister(@RequestParam String value, HttpServletRequest request){
>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960

/*
        String mId = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();
        String grade = request.getParameter("grade").trim();
        String major = request.getParameter("major").trim();
<<<<<<< HEAD
        String contact = request.getParameter("contact").trim();
        String type = request.getParameter("select").trim();


        System.out.print("aaa");
        //JSONObject jsonObject = JSONObject.fromObject(value);
       // System.out.print(jsonObject);


       //studentService.setPersonalInfo(mId,name,grade,major,contact,password);
=======
        String contact = request.getParameter("contact").trim();*/


        System.out.print(value);

        /*
        if(id.equals("student")){
            studentService.setPersonalInfo(mId,name,grade,major,contact,password);
        }else if(id.equals("teacher")){
        }*/



>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960

        return "redirect:/home";
    }
}
