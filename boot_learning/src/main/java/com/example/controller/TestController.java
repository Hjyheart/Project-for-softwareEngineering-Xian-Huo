package com.example.controller;

<<<<<<< HEAD
import com.example.entity.Comment;
import com.example.service.ActivityService;
import com.example.service.StorageService;
=======
import com.example.entity.Club;
import com.example.service.ClubService;
import com.example.service.CommentService;
import com.example.service.StudentService;
>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by deado on 2016/10/23.
 */
@Controller
@RequestMapping("/Test")
public class TestController {

    @Autowired
<<<<<<< HEAD
    private ActivityService activityService;
    @Autowired
    private StorageService storageService;

    @RequestMapping("/nameapply")
    public String nameApply(){
        try{
            this.activityService.addStudentToActivity("1452716","123");
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }

    }


    @RequestMapping("/activitycomment")
    public String activityComment(){
        try{
            Comment comment = new Comment("1452716", "123", 1, "qiangwudi", new Date());

            this.activityService.addCommentToActivity(comment, "123");
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }
    }

    @RequestMapping("/unnameapply")
    public String unnameApply(){
        try{
            this.activityService.deleteStudentFromActivity("1452716", "123");
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }
=======
    CommentService commentService = new CommentService();

    @Autowired
    StudentService studentService = new StudentService();

    @Autowired
    ClubService clubService = new ClubService();

    @RequestMapping("/Hello")
    public String Hello(ModelMap map, HttpServletRequest request){
        //map.addAttribute("comment",commentService.findAllComment("123").iterator().next().getmContent()); 学生评论

        //map.addAttribute("club",studentService.getStudentClub("123").size()); //获得参与俱乐部

       // map.addAttribute("activity",studentService.getStudentActivity("123").size());

        //map.addAttribute("apply",clubService.studentApplyOrQuitClub("1","123",true));添加申请人
       //map.addAttribute("apply",clubService.studentApplyClub("3","123"));

        /*
        try{
            map.addAttribute("quit",clubService.studentQuitClub("3","123"));
        }catch (Exception ex){
        }*/


        //map.addAttribute("club_comment",clubService.getClubComment("1").iterator().next().getmContent());添加对俱乐部的评论





        return "Hello";
>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
    }


    @RequestMapping("/good")
    public String good(){
        try{
            this.activityService.addPraise("123");
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request){
       try{
            String fileUrl = "E:/Javaspace/XianHuoSpace/README.md";
            this.storageService.uploadWithBreak(fileUrl, "test.md");
            return "UploadFile";
        }catch(Exception e){
           return "UploadFile";
        }
    }

    @RequestMapping(value="")
    public String upPage(ModelMap map){
        map.addAttribute("name", "UploadView");
        return "UploadFile";
    }

}
