package com.example.controller.web;

import com.example.entity.*;
import com.example.service.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
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
    private ClubService clubService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MessageService messageService;

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
    public void uploadFile(@RequestParam MultipartFile file, @RequestParam Long id){
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param filename
     * 文件名
     * @param id
     * 社团对应的id
     */
    @RequestMapping(value = "/deletefile", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteFile(@RequestParam String filename, @RequestParam Long id){
        try{
            Club club = clubService.findByMId(id).iterator().next();
//            qiniuService.deleteFile(filename);
            for (ClubFile clubFile : club.getClubfiles()){
                if (clubFile.getmName().equals(filename)){
                    club.getClubfiles().remove(clubFile);
                    fileService.remove(clubFile);
                    break;
                }
            }
            clubService.save(club);
            return true;
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

    /**
     * 设置社团的封面
     * @param id
     * 社团对应的id
     * @param file
     * 头像
     * @return void
     */
    @RequestMapping(value = "/changecover", method = RequestMethod.POST, produces="text/html;charset=utf-8")
    @ResponseBody
    public void changeCover(@RequestParam MultipartFile file, @RequestParam Long id){
        try{
            Club club = clubService.findByMId(id).iterator().next();
            String key = file.getOriginalFilename();
            if (qiniuService.storeFile(file)){
                club.setmImgUrl(qiniuService.createDownloadUrl("http://" + qiniuService.getDomain() + "/" + key));
                clubService.save(club);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 编辑社团基本信息
     * @param id
     * 社团对应id
     * @param name
     * 社团新名字
     * @param des
     * 社团新的des
     * @return void
     */
    @RequestMapping(value = "/editbasicinfo", method = RequestMethod.POST)
    @ResponseBody
    public void editBasicInfo(@RequestParam Long id, @RequestParam String name, @RequestParam String des){
        try{
            Club club = clubService.findByMId(id).iterator().next();
            club.setmName(name);
            club.setmDescription(des);

            clubService.save(club);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建活动
     * @Param id
     * 社团id
     * @param name
     * 名字
     * @param location
     * 地点
     * @param time
     * 时间
     * @param des
     * 描述
     * @param contact
     * 联系人
     * @param file
     * 图片
     * @return void
     */
    @RequestMapping(value = "/addactivity", method = RequestMethod.POST)
    @ResponseBody
    public void addActivity(@RequestParam Long id, @RequestParam String name, @RequestParam String location, @RequestParam String time,
                            @RequestParam String des, @RequestParam String contact, @RequestParam MultipartFile file){
        try{

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy/HH/mm");
            Date date = sdf.parse(time);
            System.out.println(date.toString());


            Club club = clubService.findByMId(id).iterator().next();
            Activity activity = new Activity();
            String key = file.getOriginalFilename();
            qiniuService.storeFile(file);
            activity.setmDescription(des);
            activity.setmName(name);
            activity.setmContact(contact);
            activity.setmImgUrl(qiniuService.createDownloadUrl("http://" + qiniuService.getDomain() + "/" + key));
            activity.setmTime(date);
            activity.setmPraise(0);
            activity.setmLocation(location);

            club.getActivities().add(activity);

            activityService.save(activity);
            clubService.save(club);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送全体广播通知
     * @param c_id
     * 社团对应id
     * @param content
     * 内容
     */
    @RequestMapping(value = "/informAll", method = RequestMethod.POST)
    @ResponseBody
    public void informAll(@RequestParam Long c_id, @RequestParam String content){
        try{
            Club club = clubService.findByMId(c_id).iterator().next();

            for (Student student : club.getStudents()){
                messageService.sendNoTemplateMessage(content, student.getmContact(), club.getmName(), student.getmName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建新的社团等待审核
     * @param s_id
     * 学生id
     * @param name
     * 社团名称
     * @param content
     * 社团标语
     * @param des
     * 社团描述
     * @param teacherName
     * 指导老师姓名
     * @Param file
     * 封面
     */
    @RequestMapping(value = "/applyforclub", method = RequestMethod.POST)
    @ResponseBody
    public void applyForClub(@RequestParam String s_id, @RequestParam String name, @RequestParam String content,
                             @RequestParam String des, @RequestParam String teacherName, @RequestParam MultipartFile file){
        try{
            Student student = studentService.findByMId(s_id).iterator().next();
            String key = file.getOriginalFilename();

            Club club = new Club();
            club.setmName(name);
            club.setmDescription(des);
            club.setContent(content);
            club.setmChairmanId(s_id);
            club.setmTeacher(teacherName);
            club.setmState(false);
            if (qiniuService.storeFile(file)) {
                club.setmImgUrl(qiniuService.createDownloadUrl("http://" + qiniuService.getDomain() + "/" + key));
            }
            student.getClubs().add(club);
            club.setmMemberNumber(1);

            clubService.save(club);
            studentService.save(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
