package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody
    String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

}
