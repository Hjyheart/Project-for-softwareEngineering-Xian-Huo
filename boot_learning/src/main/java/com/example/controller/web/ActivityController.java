package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.*;
import com.taobao.api.ApiException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

    @RequestMapping("")
    // 活动合集主页
    public String activities(ModelMap map){
        map.addAttribute("name", "activities");

        return "web/activity/home";
    }

    /**
     * 返回所有活动
     * @return
     */
    @RequestMapping(value = "/allactivity", method = RequestMethod.POST)
    @ResponseBody
    public List<Activity> getAllActivity(){
        try{
            return activityService.findAllActivities();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{id}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable String id , ModelMap map, HttpServletRequest request){
        map.addAttribute("id", id);

        return "web/activity/detail";
    }

    /**
     * 返回活动细节
     * @param id
     * 活动id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public Activity activityDetail(@RequestParam Long id){
        try{
            Activity activity = activityService.findActivityById(id).iterator().next();

            return activity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否注册
     * @param a_id
     * 活动id
     * @param s_id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/isregister", method = RequestMethod.POST)
    @ResponseBody
    public boolean isRegister(@RequestParam Long a_id, @RequestParam String s_id){
        try{
            Activity activity = activityService.findActivityById(a_id).iterator().next();
            Student student = studentService.findByMId(s_id).iterator().next();

            for (Activity activity1 : student.getActivities()){
                if (activity1.equals(activity))
                    return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
                    activity.setmState(false);
                    activityService.save(activity);
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

    /**
     * 申请活动
     * @param a_id
     * 活动id
     * @param s_id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public boolean applyActivity(@RequestParam Long a_id, @RequestParam String s_id){
        try{
            Activity activity = activityService.findActivityById(a_id).iterator().next();
            Student student = studentService.findByMId(s_id).iterator().next();

            student.getActivities().add(activity);
            activity.getStudents().add(student);
            activity.setmPraise(activity.getmPraise() + 1);

            studentService.save(student);
            activityService.save(activity);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 退出某个活动
     * @param a_id
     * 活动id
     * @param s_id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    @ResponseBody
    public boolean unApplyActivity(@RequestParam Long a_id, @RequestParam String s_id){
       try{
           Activity activity = activityService.findActivityById(a_id).iterator().next();
           Student student = studentService.findByMId(s_id).iterator().next();

           student.getActivities().remove(activity);
           activity.getStudents().remove(student);
           activity.setmPraise(activity.getmPraise() - 1);

           studentService.save(student);
           activityService.save(activity);

           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }

    }

    /**
     * 添加评论
     * @param a_id
     * 活动id
     * @param content
     * 评论内容
     * @param s_id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    @ResponseBody
    public Comment commentForActivity(@RequestParam Long a_id, @RequestParam String content, @RequestParam String s_id){
        try{
            Activity activity = activityService.findActivityById(a_id).iterator().next();
            Student student = studentService.findByMId(s_id).iterator().next();

            Comment comment = new Comment();
            comment.setActivity(activity);
            comment.setmContent(content);
            comment.setmStudentId(s_id);
            comment.setmTargetType(1);
            comment.setStudentName(student.getmName());
            comment.setmTargetId(activity.getmId());
            comment.setmDate(Date.from(Instant.now()));

            activity.getComments().add(comment);

            commentService.save(comment);
            activityService.save(activity);

            return comment;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editActivity(@RequestParam Long id, @RequestParam String name, @RequestParam String location, @RequestParam String time,
                                @RequestParam String des, @RequestParam String contact){
        try{
            Activity activity = activityService.findActivityById(id).iterator().next();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
            Date date = sdf.parse(time);
            System.out.println(date.toString());

            activity.setmName(name);
            activity.setmDescription(des);
            activity.setmLocation(location);
            activity.setmContact(contact);
            activity.setmTime(date);

            activityService.save(activity);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
