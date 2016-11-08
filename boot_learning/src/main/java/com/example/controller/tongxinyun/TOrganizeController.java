package com.example.controller.tongxinyun;

import com.example.entity.Club;
import com.example.service.ClubService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjiayong on 2016/10/29.
 */

@Controller
@RequestMapping("/t_organize")
public class TOrganizeController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("")
    // 返回社团合集
    public String organize(ModelMap map, HttpServletRequest request){

        List<Club> clubSet = clubService.findByMId("1");

        map.addAttribute("organizes", clubSet);

        return "tongxinyun/organizes";
    }

    @RequestMapping("/refresh")
    public String refresh(ModelMap map){
        ArrayList<TActivityController.activity> activityList = new ArrayList<>();

        activityList.add(new TActivityController.activity("liyang", "wudi", "lala"));
        activityList.add(new TActivityController.activity("liyang", "lala", "lala"));

        map.addAttribute("organizes", activityList);

        return "organizes :: organizeList";
    }

    @RequestMapping(value = "/{name}")
    public String showOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name);

        return "tongxinyun/organize";
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
    public @ResponseBody ArrayList<TActivityController.activity> commentRefresh(@PathVariable String name, int start, int number, HttpServletRequest request){
        ArrayList<TActivityController.activity> activityList = new ArrayList<>();
        activityList.add(new TActivityController.activity("liyang", "wudi", "lala"));
        activityList.add(new TActivityController.activity("liyang", "lala", "lala"));

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

