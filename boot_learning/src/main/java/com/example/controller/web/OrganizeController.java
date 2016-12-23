package com.example.controller.web;

import com.example.entity.*;
import com.example.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private QiniuService qiniuService = new QiniuService();

    @Autowired
    private FileService fileService = new FileService();

    @Autowired
    private CommentService commentService = new CommentService();

    /**
     * 获取社团集锦的网页模板
     * @return clubsView
     */
    @RequestMapping("")
    public String organizes(ModelMap map){
        map.addAttribute("name", "organize");

        return "web/home";
    }

    /**
     * 获取某一个社团详情的网页模板
     * @param  id
     * 社团对应的id
     * @return clubView
     */
    @RequestMapping(value = "/{id}")
    public String clubView(@PathVariable Long id, ModelMap map, HttpServletRequest request){

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

    /**
     * 获取某一个社团之间的详情
     * @param id
     * 社团对应的id
     * @return club
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public Club getClubDetail(@RequestParam Long id){
        try{
            Club club = clubService.findByMId(id).iterator().next();

            return club;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取某一个学生和某个社团之间的关系
     * @param c_id
     * 社团对应的id
     * @param s_id
     * 学生对应的id
     * @return 没申请-> false 申请了-> true
     */
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    @ResponseBody
    public boolean getState(@RequestParam String s_id, @RequestParam Long c_id){
        try {
            Student stu = studentService.findByMId(s_id).iterator().next();

            for (Club club : stu.getClubs()){
                if (club.getmId().equals(c_id)){
                    return true;
                }
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 在某个社团中加入某个学生
     * @param c_id 社团对应的id
     * @param s_id 学生对应的id
     * @return boolean
     */
    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    @ResponseBody
    public boolean addStudent(@RequestParam String s_id, @RequestParam Long c_id){
        try {
            if (clubService.studentApplyClub(c_id, s_id)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除某个社团中的某个学生
     * @param c_id
     * 社团对应的id
     * @param s_id
     * 学生对应的id
     * @return boolean
     */
    @RequestMapping(value = "/deletestudent", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteStudent(@RequestParam String s_id, @RequestParam Long c_id){
        try {
            if (clubService.studentQuitClub(c_id, s_id)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回社团所有的活动
     * @param  id
     * 社团对应的id
     * @return
     * 社团所有活动
     */
    @RequestMapping(value = "/getactivity", method = RequestMethod.POST)
    @ResponseBody
    public List<Activity> getActivity(@RequestParam Long id){
        try{
            Club c = clubService.findByMId(id).iterator().next();
            List<Activity> activities = c.getActivities();

            return activities;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 返回社团所有的评论
     * @param id
     * 社团对应的id
     * @return
     * 社团所有评论
     */
    @RequestMapping(value = "/getcomment", method = RequestMethod.POST)
    @ResponseBody
    public List<Comment> getComment(@RequestParam Long id){
        try{
            Club c = clubService.findByMId(id).iterator().next();
            List<Comment> comments = c.getComments();

            return comments;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 添加对社团的评论
     * @param c_id
     * 社团对应id
     * @param s_id
     * 学生对应id
     * @return comment
     * 所添加的评论
     */
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    @ResponseBody
    public Comment addComment(@RequestParam Long c_id, @RequestParam String s_id, @RequestParam String content){
        try{
            Club c = clubService.findByMId(c_id).iterator().next();
            Student stu = studentService.findByMId(s_id).iterator().next();

            Comment comment = new Comment();
            comment.setClub(c);
            comment.setmDate(Date.from(Instant.now()));
            comment.setmStudentId(s_id);
            comment.setmTargetType(0);
            comment.setmTargetId(c_id);
            comment.setStudentName(stu.getmName());
            comment.setmContent(content);
            commentService.save(comment);

            c.getComments().add(comment);
            clubService.save(c);

            return comment;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传文件
     * @param file
     * 文件本地路径
     * @param id
     * 社团对应的id
     * @return boolean
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces="text/html;charset=utf-8")
    @ResponseBody
    public boolean uploadFile(@RequestParam MultipartFile file, @RequestParam Long id){
        try{
            Club c = clubService.findByMId(id).iterator().next();
            if (qiniuService.storeFile(file)){
                String key = file.getOriginalFilename();
                ClubFile clubFile = new ClubFile();
                clubFile.setmName(key);
                clubFile.setmClub(c.getmName());
                clubFile.setmUrl("http://" + qiniuService.getDomain() + "/" + key);
                fileService.save(clubFile);
                c.getClubfiles().add(clubFile);
                clubService.save(c);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取某俱乐部的资源文件
     * @param clubId
     * 俱乐部的id
     * @return 文件map
     */
    @RequestMapping(value = "/getfiles", method = RequestMethod.POST)
    @ResponseBody
    public Map getFiles(@RequestParam Long clubId){
        try{
            Club c = clubService.findByMId(clubId).iterator().next();
            Map map = new HashMap<>();

            List<ClubFile> clubList = c.getClubfiles();

            for (ClubFile clubfile : clubList){
                clubfile.setmUrl(qiniuService.createDownloadUrl(clubfile.getmUrl()));
            }

            map.put("club_files", clubList);

            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回社团管理页面
     * @param  id
     * 社团对应的id
     * @return 社团视图
     */
    @RequestMapping(value = "/{id}/manage")
    public String manageView(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("clubId", id);

        return "web/club/manage";
    }

    /**
     * 返回学生和社团之间的关系
     * @param s_id
     * 学生对应的id
     * @param c_id
     * 社团对应的id
     * @return boolean
     */
    @RequestMapping(value = "/vertifyclubhost", method = RequestMethod.POST)
    @ResponseBody
    public boolean vertifyClubHost(@RequestParam String s_id, @RequestParam Long c_id){
        try{
            Club club = clubService.findByMId(c_id).iterator().next();
            if (club.getmChairmanId().equals(s_id)){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回一个社团所有的学生的信息
     * @param c_id
     * 社团对应的id
     * @return students
     */
    @RequestMapping(value = "/getstudents", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> getStudents(@RequestParam Long c_id){
        try{
            Club club = clubService.findByMId(c_id).iterator().next();
            return club.getStudents();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
