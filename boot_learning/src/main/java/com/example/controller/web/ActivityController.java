package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Student;
import com.example.service.ActivityService;
import com.example.service.ClubService;
import com.example.service.MessageService;
import com.example.service.StudentService;
import com.taobao.api.ApiException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("")
    // 活动合集主页
    public String activities(ModelMap map){
        map.addAttribute("name", "activities");

        return "web/activity/home";
    }

    @RequestMapping(value = "/{id}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable String id , ModelMap map, HttpServletRequest request){
        map.addAttribute("name", id);

        return "web/home";
    }

    /**
     * 删除某个活动
     * @param a_id
     * 活动id
     * @param c_id
     * 学生id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteActiivity(@RequestParam Long a_id, @RequestParam Long c_id){
        try{
            Activity activity = activityService.findActivityById(a_id).iterator().next();
            Club club = clubService.findByMId(c_id).iterator().next();

            for (Activity activity1 : club.getActivities()){
                if (activity1.getmId().equals(a_id)){
                    club.getActivities().remove(activity1);
                    activityService.deleteActivityBymId(a_id);
                    break;
                }
            }

            clubService.save(club);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 群发推广活动
     * @param a_id
     * 活动id
     * @Param c_id
     * 社团id
     * @throws ApiException
     */
    @RequestMapping(value = "/informAll", method = RequestMethod.POST)
    @ResponseBody
    public void informAll(@RequestParam Long a_id, @RequestParam Long c_id) throws ApiException {
        try{
            Club club = clubService.findByMId(c_id).iterator().next();
            Activity activity = activityService.findActivityById(a_id).iterator().next();

            for (Student student : club.getStudents()){
                messageService.sendMessage(student.getmName(), student.getmContact(), activity.getmName(), activity.getmLocation(), activity.getmTime(), activity.getmDescription());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
