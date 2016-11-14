package com.example.controller.tongxinyun;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.service.ActivityService;
import com.example.service.ClubService;
import com.example.service.CommentService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.example.controller.tongxinyun.SessionController.getUsername;

/**
 * Created by hongjiayong on 2016/10/25.
 */
@Controller
@RequestMapping("/t_stu")
public class TStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ClubService clubService;

    @RequestMapping("/mycomment")
    // 显示我的评论列表
    public String myComment(ModelMap map, HttpServletRequest request) throws Exception {

        String username = getUsername(request);
        List<Club> clubList = clubService.findAll();
        List<Activity> activityList = activityService.findAllActivities();

        ArrayList<Club> clubs = new ArrayList<>();
        ArrayList<Activity> activities = new ArrayList<>();

        for (Club club : clubList){
            List<Comment> commentList = club.getComments();
            for (Comment comment : commentList){
                if (comment.getmStudentId().equals(username)){
                    clubs.add(club);
                    break;
                }
            }
        }

        for (Activity activity : activityList){
            List<Comment> commentList = activity.getComments();
            for (Comment comment : commentList){
                if (comment.getmStudentId().equals(username)){
                    activities.add(activity);
                    break;
                }
            }
        }

        map.addAttribute("clubs", clubs);
        map.addAttribute("activities", activities);
        map.addAttribute("username", username);

        return "tongxinyun/mycomment";
    }

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping("/myorganize")
    // 返回加入社团的列表
    public String myOrganize(ModelMap map, HttpServletRequest request){
        ArrayList <TActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new TActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new TActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("organizes", organizeList);

        return "tongxinyun/myorganize";
    }

    @RequestMapping("/myactivity")
    // 返回申请和玩过的活动
    public String myActivity(ModelMap map, HttpServletRequest request){
        ArrayList <TActivityController.activity> organizeList = new ArrayList<>();

        organizeList.add(new TActivityController.activity("李阳", "无敌", "2016/10.27"));
        organizeList.add(new TActivityController.activity("洪嘉勇", "菜鸡", "2016/10.27"));

        map.addAttribute("activities", organizeList);

        return "tongxinyun/myactivity";
    }

}
