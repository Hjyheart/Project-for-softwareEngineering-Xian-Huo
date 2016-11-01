package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by hongjiayong on 2016/10/29.
 */

@Controller
@RequestMapping("/organize")
public class OrganizeController {

    @RequestMapping("")
    // 返回社团合集
    public String organize(ModelMap map, HttpServletRequest request){
        ArrayList<ActivityController.activity> activityList = new ArrayList<>();

        activityList.add(new ActivityController.activity("c4", "wudi", "lala"));
        activityList.add(new ActivityController.activity("c4", "wudi", "lala"));

        map.addAttribute("organizes", activityList);

        return "organizes";
    }

    @RequestMapping("/refresh")
    public String refresh(ModelMap map){
        ArrayList<ActivityController.activity> activityList = new ArrayList<>();

        activityList.add(new ActivityController.activity("liyang", "wudi", "lala"));
        activityList.add(new ActivityController.activity("liyang", "lala", "lala"));

        map.addAttribute("organizes", activityList);

        return "organizes :: organizeList";
    }

    @RequestMapping(value = "/{name}")
    public String showOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name);

        return "organize";
    }

    @RequestMapping(value = "/{name}/apply")
    // 申请参加社团
    public @ResponseBody String applyOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping(value = "/{name}/unapply")
    // 取消申请社团
    public @ResponseBody String unApplyActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping(value = "/{name}/comment", method = RequestMethod.GET)
    // 进行评论
    public @ResponseBody String commentOrganize(@PathVariable String name, String comment, HttpServletRequest servletRequest){
        System.out.println(comment);

        return "true";
    }

    @RequestMapping(value = "/{name}/comment/refresh", method = RequestMethod.GET)
    // 异步加载评论
    public @ResponseBody ArrayList<ActivityController.activity> commentRefresh(@PathVariable String name, int start, int number, HttpServletRequest request){
        ArrayList<ActivityController.activity> activityList = new ArrayList<>();
        activityList.add(new ActivityController.activity("liyang", "wudi", "lala"));
        activityList.add(new ActivityController.activity("liyang", "lala", "lala"));

        return activityList;
    }

    @RequestMapping(value = "/{name}/good")
    // 点赞
    public @ResponseBody String goodForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest  request){
        return "false";
    }

    @RequestMapping(value = "/{name}/ungood")
    // 取消点赞
    public @ResponseBody String unGoodForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        return "true";
    }
}

