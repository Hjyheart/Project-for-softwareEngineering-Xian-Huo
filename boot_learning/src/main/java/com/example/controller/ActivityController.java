package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @RequestMapping("")
    // 活动合集主页
    public String activities(ModelMap map){
        map.addAttribute("name", "activities");

        return "home";
    }

    @RequestMapping(value = "/{name}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name);
        System.out.println(request.getRequestURI());

        return "home";
    }

    @RequestMapping(value = "/{name}/apply")
    // 申请参加活动
    public @ResponseBody String applyActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";

    }

    @RequestMapping(value = "/{name}/unapply")
    // 取消申请参加活动
    public @ResponseBody String unApplyActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";

    }

    @RequestMapping(value = "/{name}/comments")
    // 查看活动对应的评论
    public String commentsForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " comments");

        return "home";
    }

    @RequestMapping(value = "/{name}/good")
    // 点赞
    public @ResponseBody String goodForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " good");

        return "true";
    }

    @RequestMapping(value = "/{name}/ungood")
    // 取消点赞
    public @ResponseBody String unGoodForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " good");

        return "true";
    }
}
