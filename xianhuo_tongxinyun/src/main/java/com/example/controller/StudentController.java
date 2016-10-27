package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping("/myorganize")
    // 返回加入社团的列表
    public String myOrganize(ModelMap map, HttpServletRequest request){
        ArrayList <ActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new ActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new ActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("organizes", organizeList);

        return "myorganize";
    }

    @RequestMapping("/myactivity")
    // 返回申请和玩过的活动
    public String myActivity(ModelMap map, HttpServletRequest request){
        return "myactivity";
    }

}
