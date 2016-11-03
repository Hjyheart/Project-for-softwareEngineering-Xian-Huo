package com.example.controller;

import com.example.entity.Club;
import com.example.service.ClubService;
import com.example.service.CommentService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deado on 2016/10/23.
 */
@Controller
@RequestMapping("/Test")
public class TestController {

    @Autowired
    CommentService commentService = new CommentService();

    @Autowired
    StudentService studentService = new StudentService();

    @Autowired
    ClubService clubService = new ClubService();

    @RequestMapping("/Hello")
    public String Hello(ModelMap map, HttpServletRequest request){
        //map.addAttribute("comment",commentService.findAllComment("123").iterator().next().getmContent()); 学生评论

        //map.addAttribute("club",studentService.getStudentClub("123").size()); //获得参与俱乐部

       // map.addAttribute("activity",studentService.getStudentActivity("123").size());

        //map.addAttribute("apply",clubService.studentApplyOrQuitClub("1","123",true));添加申请人
       //map.addAttribute("apply",clubService.studentApplyClub("3","123"));

        /*
        try{
            map.addAttribute("quit",clubService.studentQuitClub("3","123"));
        }catch (Exception ex){
        }*/


        //map.addAttribute("club_comment",clubService.getClubComment("1").iterator().next().getmContent());添加对俱乐部的评论





        return "Hello";
    }
}
