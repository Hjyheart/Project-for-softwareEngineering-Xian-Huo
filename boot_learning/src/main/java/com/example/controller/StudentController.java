package com.example.controller;

import com.example.entity.Student;
import com.example.entity.StudentReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    private StudentReposity studentReposity;

    @RequestMapping("/list")
    public String getStus(){
//        logger.info("从数据库读取Student集合");
//        return studentService.getList();
        studentReposity.save(new Student("1452551", 10));
        studentReposity.save(new Student("1452552", 11));

        return "home";
    }

}
