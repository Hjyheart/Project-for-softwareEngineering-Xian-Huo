package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

    @RequestMapping("/basic")
    // 显示学生的个人管理主页 内包括学生个人信息
    public String basic(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "stu basic");

        return "home";
    }

    @RequestMapping("/mycomments")
    // 显示用户的评论列表 支持跳转到该活动
    public String myComments(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "comments");

        return "home";
    }

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping("/myorganizes")
    // 显示用户加入的社团列表 点击之后重定向到该社团
    public String myOrganizes(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "organizes");

        return "home";
    }

    @RequestMapping("/myactivities")
    // 显示用户参加的活动列表 支持跳转到该活动
    public String myActivities(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "myActivities");

        return "home";
    }

    @RequestMapping("/myFavActivity")
    // 显示用户收藏的活动列表
    public String myFavActivities(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "myFavActivities");

        return "home";
    }
}
