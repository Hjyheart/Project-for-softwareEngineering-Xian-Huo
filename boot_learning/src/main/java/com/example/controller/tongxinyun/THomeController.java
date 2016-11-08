package com.example.controller.tongxinyun;

import com.example.entity.Student;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

import static com.example.controller.tongxinyun.SessionController.getUsername;
import static com.example.controller.tongxinyun.SessionController.setMySession;
import static com.example.controller.tongxinyun.SessionController.vertifySession;


/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/t_home")
public class THomeController {

    private String username;
    private Student student;

    @Autowired
    private StudentService studentService;

    @RequestMapping("")
    public String home(ModelMap map, HttpServletRequest request){
        if (!vertifySession(request)){
            return "error";
        }else{
            username = getUsername(request);
        }

        List<Student> studentSet = studentService.findByMId(username);
        student = studentSet.iterator().next();
        map.addAttribute("username", student.getmName());
        map.addAttribute("studentId", student.getmId());

        return "tongxinyun/home";
    }


    @RequestMapping("/add")
    public String test(HttpServletRequest request){
        setMySession(request, "1454093");
        System.out.println("登录");
        return "error";
    }




    public static class testUser{
        private String name;
        private int age;
        private String description;

        public testUser(String name, int age, String description){
            this.name = name;
            this.age = age;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

    }


}
