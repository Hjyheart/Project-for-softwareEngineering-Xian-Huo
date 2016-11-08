package com.example.controller.tongxinyun;

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
@RequestMapping("/t_stu")
public class TStudentController {

    @RequestMapping("/mycomment")
    // 显示我的评论列表
    public String myComment(ModelMap map, HttpServletRequest request){
        ArrayList <TActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new TActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new TActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("comments", organizeList);

        return "tongxinyun/mycomment";
    }

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping("/myorganize")
    // 返回加入社团的列表
    public String myOrganize(ModelMap map, HttpServletRequest request){
        ArrayList <TActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new TActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new TActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("organizes", organizeList);

        return "tongxinyun/myorganize";
    }

    @RequestMapping("/myactivity")
    // 返回申请和玩过的活动
    public String myActivity(ModelMap map, HttpServletRequest request){
        ArrayList <TActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new TActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new TActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("activities", organizeList);

        return "tongxinyun/myactivity";
    }

}
