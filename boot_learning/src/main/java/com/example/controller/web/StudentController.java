package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Student;
import com.example.service.ActivityService;
import com.example.service.ClubService;
import com.example.service.QiniuService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Controller
@RequestMapping("/mycenter")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private QiniuService qiniuService;

    @RequestMapping("/profile")
    // 显示学生的个人管理主页 内包括学生个人信息
    public String basic(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "个人信息主页");

        return "web/mycenter/profile";
    }

    @RequestMapping("/mycomments")
    // 显示用户的评论列表 支持跳转到该活动
    public String myComments(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "comments");

        return "web/home";
    }

    @RequestMapping(value = "/deletecomment")
    public @ResponseBody String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping(value = "/myclubs", method = RequestMethod.POST)
    @ResponseBody
    // 显示用户加入的社团列表 点击之后重定向到该社团
    public List<Club> myOrganizes(@RequestParam String id){

        Student stu = studentService.findByMId(id).iterator().next();

        List<Club> clubList = stu.getClubs();


        return clubList;
    }

    /**
     * 列出用户建立的club
     * @param id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/myhostclub", method = RequestMethod.POST)
    @ResponseBody
    // 获取用户建立的俱乐部
    public Map myHostClub(@RequestParam String id){
        try{
            List<Club> clubList = clubService.findAll();
            Map map = new HashMap<>();
            ArrayList<Club> clubs = new ArrayList<>();

            for (Club club : clubList){
                if (club.getmChairmanId().equals(id)){
                    clubs.add(club);
                }
            }
            map.put("host_clubs", clubs);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 列出我的活动列表
     * @param id
     * 学生id
     * @return
     */
    @RequestMapping(value = "/myactivities", method = RequestMethod.POST)
    @ResponseBody
    public List<Activity> myActivities(@RequestParam String id){
        try{
            Student student = studentService.findByMId(id).iterator().next();

            return student.getActivities();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getstu", method = RequestMethod.POST)
    @ResponseBody
    public Student getStudent(HttpServletRequest request){
        try{
            String id = request.getParameter("id").trim();
            Student stu = studentService.findByMId(id).iterator().next();
            return stu;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更换头像
     * @param id
     * 学生id
     * @param file
     * 头像文件
     */
    @RequestMapping(value = "/changehead", method = RequestMethod.POST)
    @ResponseBody
    public void changeHead(@RequestParam String id, @RequestParam MultipartFile file){
        try{
            Student student = studentService.findByMId(id).iterator().next();
            String key = file.getOriginalFilename();
            if (qiniuService.storeFile(file)){
                student.setmHeadUrl(qiniuService.createDownloadUrl("http://" + qiniuService.getDomain() + "/" + key));
                studentService.save(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
