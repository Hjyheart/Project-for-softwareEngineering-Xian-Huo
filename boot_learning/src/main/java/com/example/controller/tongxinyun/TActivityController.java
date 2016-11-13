package com.example.controller.tongxinyun;

import com.example.entity.Activity;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.ActivityService;
import com.example.service.CommentService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.example.controller.tongxinyun.SessionController.getUsername;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/t_activity")
public class TActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("")
    // 活动合集首页
    public String activities(ModelMap map) throws Exception {
        List<Activity> activityList = activityService.findAllActivities();

        map.addAttribute("activities", activityList);

        return "tongxinyun/activities";
    }

    @RequestMapping("/refresh")
    public String refresh(ModelMap map) throws Exception {
        List<Activity> activityList = activityService.findAllActivities();

        map.addAttribute("activities", activityList);

        return "tongxinyun/activities :: activityList";
    }

    @RequestMapping(value = "/{name}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        System.out.println(name);
        try {
            List<Activity> activitySet = this.activityService.findByName(name);
            List<Comment> commentList = commentService.findAllComment("1452822");

            if (commentList.size() >= 10){
                map.addAttribute("comments", commentList.subList(0, 9));
                map.addAttribute("lastIndex", 10);
            }else{
                map.addAttribute("comments", commentList);
                map.addAttribute("lastIndex", commentList.size());
            }

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


    @RequestMapping(value = "/{name}/comment", method = RequestMethod.GET)
    // 进行评论
    public @ResponseBody String commentActivity(@PathVariable String name, String comment, HttpServletRequest request){
        System.out.println(comment);
//        commentService.save(new Comment(getUsername(request), "1", 1, comment, java.util.Date.from(Instant.now())));

        return "true";
    }

    @RequestMapping(value = "/{name}/comment/refresh", method = RequestMethod.GET)
    // 异步加载评论
    public String commentRefresh(@PathVariable String name, int start, int number, ModelMap map){
        List<Comment> commentList = commentService.findAllComment("1452822");

        if (start + number <= commentList.size()){
            map.addAttribute("comments", commentList.subList(start - 1, number + start));
        }else if(start < commentList.size()) {
            map.addAttribute("comments", commentList.subList(start - 1, commentList.size() - 1));
        }

        return "tongxinyun/fragments :: commentList";
    }

    @RequestMapping(value = "/{name}/good")
    // 点赞
    public @ResponseBody String goodForActivity(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " good");
        try {
            List<Student> students = studentService.findByMId(getUsername(request));
            Student user = students.iterator().next();

            for (Activity activity : user.getFavouriteactivities()) {
                if (activity.getmName().equals(name)) {
                    return "false";
                }
            }

            Activity addActivity = activityService.findByName(name).iterator().next();

            studentService.addFavouriteActivity(getUsername(request), addActivity);

            return "true";
        }catch (Exception e){
            return "login";
        }
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
