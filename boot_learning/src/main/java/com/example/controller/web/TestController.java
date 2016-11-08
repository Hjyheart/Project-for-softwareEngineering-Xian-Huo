package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Comment;
import com.example.service.ActivityService;
import com.example.service.StorageService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
<<<<<<< HEAD:boot_learning/src/main/java/com/example/controller/TestController.java
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> d359bb61eac471da3a33dad60d35763988a18b78:boot_learning/src/main/java/com/example/controller/web/TestController.java
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Set;

/**
 * Created by deado on 2016/10/23.
 */
@Controller
@RequestMapping("/Test")
public class TestController {

    @Autowired
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
    public String unnameApply() {
        try {
            this.activityService.deleteStudentFromActivity("1452716", "123");
            return "Success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Fail";
        }
    }


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

    @RequestMapping(value="/upload", method = RequestMethod.POST, produces="text/html;charset=utf-8")
    public String upload(@RequestParam(value = "fileinput")MultipartFile mulFile, HttpServletRequest request){
       try{
           this.storageService.storeFile(mulFile, "TestClub");
           return "redirect:/Test";
        }catch(Exception e){
           e.printStackTrace();
           return "redirect:/Test";
        }
    }

    @RequestMapping(value="")
    public String upPage(ModelMap map){
        map.addAttribute("name", "UploadView");
        return "UploadFile";
    }

    @RequestMapping(value="/FindByNameTest")
    public String byNameTest(){
        Set<Activity> res = this.activityService.findByName("谷歌编程一小时");
        return "fuck";
    }

}
