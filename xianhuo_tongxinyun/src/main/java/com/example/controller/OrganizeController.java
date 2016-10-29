package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
