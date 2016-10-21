package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @RequestMapping("")
    // 活动主页
    public String activities(ModelMap map){
        map.addAttribute("name", "activities");

        return "home";
    }

    @RequestMapping(value = "/{name}")
    // 根据活动名称查找活动
    public String showActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name);
        System.out.println(request.getRequestURI());

        return "home";
    }
}
