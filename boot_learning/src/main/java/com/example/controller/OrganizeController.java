package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/22.
 */
@Controller
@RequestMapping("/organize")
public class OrganizeController {

    @RequestMapping("")
    // 社团合集主页
    public String organizes(ModelMap map){
        map.addAttribute("name", "organize");

        return "home";
    }

    @RequestMapping(value = "/{name}")
    // 根据名称查找展现社团
    public String showOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name);

        return "home";
    }

    @RequestMapping(value = "/{name}/apply")
    // 申请加入社团
    public @ResponseBody String applyOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping(value = "/{name}/unapply")
    // 退出社团
    public @ResponseBody String unApplyOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping(value = "/{name}/comments")
    // 查看社团对应的评论
    public String commentsForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " comments");

        return "home";
    }

    @RequestMapping(value = "/{name}/pics")
    // 查看社团的照片
    public String picForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " pics");

        return "home";
    }

    @RequestMapping(value = "/{name}/sources")
    public String sourceForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " sources");

        return "home";
    }
}
