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

import java.time.Instant;
import java.util.List;

import static com.example.controller.tongxinyun.SessionController.getUsername;
import static com.example.controller.tongxinyun.SessionController.vertifySession;

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

        return "fragments :: activityList";
    }

    @RequestMapping(value = "/{mId}")
    // 根据活动名称展现活动
    public String showActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){

        try {
            List<Activity> activitySet = this.activityService.findActivityById(mId);
            List<Comment> commentList = commentService.findAllCommentOfActivity(mId);

            if (commentList.size() >= 10){
                for (Comment comment:commentList.subList(0 ,9)){
                    comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
                }
                map.addAttribute("comments", commentList.subList(0, 9));
                map.addAttribute("lastIndex", 10);
            }else{
                for (Comment comment:commentList){
                    comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
                }
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

    @RequestMapping(value = "/{mId}/apply")
    // 申请参加活动
    public @ResponseBody
    String applyActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){

        try{
            List<Student> studentList = studentService.findByMId(getUsername(request));
            List<Activity> activityList = studentList.iterator().next().getActivities();

            for (Activity activity : activityList){
                if (activity.getmId() == mId){
                    return "false";
                }
            }

            activityService.addStudentToActivity(getUsername(request), mId);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping(value = "/{mId}/unapply")
    // 取消申请参加活动
    public @ResponseBody String unApplyActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){

        try{
            activityService.deleteStudentFromActivity(getUsername(request), mId);
            return "true";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping(value = "/{mId}/comment", method = RequestMethod.GET)
    // 进行评论
    public @ResponseBody String commentActivity(@PathVariable Long mId, String comment, HttpServletRequest request) throws Exception {
        System.out.println(comment);
        if(!vertifySession(request)){
            return "login";
        }
        Comment newComment = new Comment(getUsername(request), mId, 1, comment, java.util.Date.from(Instant.now()), getUsername(request));
        activityService.addCommentToActivity(newComment, mId);

        return "true";
    }

    @RequestMapping(value = "/{mId}/comment/refresh", method = RequestMethod.GET)
    // 异步加载评论
    public String commentRefresh(@PathVariable Long mId, int start, int number, ModelMap map) throws Exception {

        List<Comment> commentList = commentService.findAllCommentOfActivity(mId);

        if (start + number <= commentList.size()){
            for (Comment comment:commentList.subList(start , number + start)){
                comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
            }
            map.addAttribute("comments", commentList.subList(start, number + start));
        }else if(start <= commentList.size()) {
            for (Comment comment:commentList.subList(start ,commentList.size())){
                comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
            }
            map.addAttribute("comments", commentList.subList(start, commentList.size()));
        }else{
            map.addAttribute("comments", null);
        }
        return "fragments :: commentList";
    }

    @RequestMapping(value = "/{mId}/good")
    // 点赞
    public @ResponseBody String goodForActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", mId + " good");
        try {
            List<Student> students = studentService.findByMId(getUsername(request));
            Student user = students.iterator().next();

            for (Activity activity : user.getFavouriteactivities()) {
                if (activity.getmId() == mId) {
                    return "false";
                }
            }

            Activity addActivity = activityService.findActivityById(mId).iterator().next();

            studentService.addFavouriteActivity(getUsername(request), addActivity);

            return "true";
        }catch (Exception e){
            return "login";
        }
    }

    @RequestMapping(value = "/{mId}/ungood")
    // 取消点赞
    public @ResponseBody String unGoodForActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", mId + " good");

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
