package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.ClubService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hongjiayong on 2016/10/22.
 */
@Controller
@RequestMapping("/club")
public class OrganizeController {

    @Autowired
    private ClubService clubService = new ClubService();

    @Autowired
    private StudentService studentService = new StudentService();

    @RequestMapping("")
    // 社团合集主页
    public String organizes(ModelMap map){
        map.addAttribute("name", "organize");

        return "web/home";
    }

    @RequestMapping(value = "/{id}")
    // 根据名称查找展现社团
    public String showOrganize(@PathVariable Long id, ModelMap map, HttpServletRequest request){

        try {
            Club club = clubService.findByMId(id).iterator().next();
            map.addAttribute("id", id);
            map.addAttribute("name", club.getmName());
        }catch (Exception e){
            e.printStackTrace();
            map.addAttribute("id", id);
            map.addAttribute("name", null);
        }

        return "web/club/detail";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public Club getClubDetail(HttpServletRequest request){
        Long id = Long.valueOf(request.getParameter("id").trim());
        try{
            Club club = clubService.findByMId(id).iterator().next();

            return club;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{name}/sources")
    public String sourceForOrganize(@PathVariable String name, ModelMap map, HttpServletRequest request){
        map.addAttribute("name", name + " sources");

        return "web/home";
    }

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    @ResponseBody
    // 获得申请状态 -1为没申请 0为申请了 1为已经是成员了
    public boolean getState(HttpServletRequest request){
        try {
            String stu_id = request.getParameter("s_id").trim();
            Long club_id = Long.valueOf(request.getParameter("c_id").trim());

            Student stu = studentService.findByMId(stu_id).iterator().next();

            for (Club club : stu.getClubs()){
                if (club.getmId().equals(club_id)){
                    return true;
                }
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    @ResponseBody
    // 加学生进社团
    public boolean addStudent(HttpServletRequest request){
        try {
            String stu_id = request.getParameter("s_id").trim();
            Long club_id = Long.valueOf(request.getParameter("c_id").trim());

            if (clubService.studentApplyClub(club_id, stu_id)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/deletestudent", method = RequestMethod.POST)
    @ResponseBody
    // 删除学生
    public boolean deleteStudent(HttpServletRequest request){
        try {
            String stu_id = request.getParameter("s_id").trim();
            Long club_id = Long.valueOf(request.getParameter("c_id").trim());

            if (clubService.studentQuitClub(club_id, stu_id)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/getactivity", method = RequestMethod.POST)
    @ResponseBody
    // 获取社团的活动
    public List<Activity> getActivity(@RequestParam(value = "c_id") Long id){
        try{
            Club c = clubService.findByMId(id).iterator().next();
            List<Activity> activities = c.getActivities();

            return activities;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/getcomment", method = RequestMethod.POST)
    @ResponseBody
    // 获取社团的活动
    public List<Comment> getComment(@RequestParam(value = "c_id") Long id){
        try{
            Club c = clubService.findByMId(id).iterator().next();
            List<Comment> comments = c.getComments();

            return comments;
        }catch (Exception e){
            return null;
        }
    }
}
