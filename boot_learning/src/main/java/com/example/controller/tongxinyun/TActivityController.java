package com.example.controller.tongxinyun;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.service.ActivityService;
import com.example.service.ClubService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/t_activity")
public class TActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClubService clubService;

    @RequestMapping("")
    // 活动合集首页
    public String activities(ModelMap map){
        Set<Club> clubSet = clubService.findByMId("1");
        Set<Activity> activitySet = clubSet.iterator().next().getActivities();

        map.addAttribute("activities", activitySet);

        return "tongxinyun/activities";
    }

    @RequestMapping("/refresh")
    public String refresh(ModelMap map){
        ArrayList<activity> activityList = new ArrayList<>();

        activityList.add(new activity("liyang", "wudi", "lala"));
        activityList.add(new activity("liyang", "lala", "lala"));

        map.addAttribute("activities", activityList);

        return "tongxinyun/activities :: activityList";
    }

    @RequestMapping(value = "/{name}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);
//        Charset.forName("UTF-8").encode(name);
        try {
            Set<Activity> activitySet = this.activityService.findByName(name);
            map.addAttribute("activity", activitySet.iterator().next());

            return "tongxinyun/activity";
        }catch (NullPointerException e){
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @RequestMapping(value = "/{name}/apply")
    // 申请参加活动
    public @ResponseBody
    String applyActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);

        return "false";

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

        return "tongxinyun/home";
    }

    @RequestMapping(value = "/{name}/comment", method = RequestMethod.GET)
    // 进行评论
    public @ResponseBody String commentActivity(@PathVariable String name, String comment, HttpServletRequest servletRequest){
        System.out.println(comment);

        return "true";
    }

    @RequestMapping(value = "/{name}/comment/refresh", method = RequestMethod.GET)
    // 异步加载评论
    public @ResponseBody ArrayList<activity> commentRefresh(@PathVariable String name, int start, int number, HttpServletRequest request){
        ArrayList<activity> activityList = new ArrayList<>();

        activityList.add(new activity("liyang", "wudi", "lala"));
        activityList.add(new activity("liyang", "lala", "lala"));

        return activityList;
    }

    @RequestMapping(value = "/{name}/good")
    // 点赞
    public @ResponseBody String goodForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " good");

        return "false";
    }

    @RequestMapping(value = "/{name}/ungood")
    // 取消点赞
    public @ResponseBody String unGoodForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " good");

        return "true";
    }



    // for testing
    public static class activity{
        private String name;
        private String desc;
        private String data;

        activity(String name, String desc, String data){
            this.name = name;
            this.desc = desc;
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public String getData() {
            return data;
        }

        public String getDesc() {
            return desc;
        }
    }
}
