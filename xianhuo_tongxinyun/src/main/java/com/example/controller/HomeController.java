package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

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

        ArrayList<testUser> userList = new ArrayList<>();

        userList.add(new testUser("洪嘉勇", 21, "无敌"));
        userList.add(new testUser("李阳", 20, "全能王"));

        map.addAttribute("users", userList);

        return "home";
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
