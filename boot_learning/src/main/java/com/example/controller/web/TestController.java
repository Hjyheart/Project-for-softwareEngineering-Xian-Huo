package com.example.controller.web;

import com.example.entity.Activity;
import com.example.entity.Comment;
import com.example.service.ActivityService;
import com.example.service.EncryptionService;
import com.example.service.StorageService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by deado on 2016/10/23.
 */
@RestController
@RequestMapping("/Test")
public class TestController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private StudentService studentService;


    @RequestMapping("/nameapply")
    public String nameApply(){
        try{
            this.activityService.addStudentToActivity("1452716", 1L);
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }

    }


    @RequestMapping("/activitycomment")
    public String activityComment(){
        try{
            Comment comment = new Comment("1452716", 1L, 1, "qiangwudi", new Date());

            this.activityService.addCommentToActivity(comment, 1L);
            return "Success";
        }catch(Exception ex){
            ex.printStackTrace();
            return "Fail";
        }
    }

    @RequestMapping("/unnameapply")
    public String unnameApply() {
        try {
            this.activityService.deleteStudentFromActivity("1452716", 1L);
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
            this.activityService.addPraise(1L);
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
        List<Activity> res = this.activityService.findByName("谷歌编程一小时");
        return "fuck";
    }

    @RequestMapping(value="/MD5")
    public boolean encryptionTest(){
        try{
            //this.studentService.addStudent("1452716","张尹嘉","2014","软件学院", "1222", "123456");
            return this.encryptionService.comparePW("1452716","123456");

        }catch(Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/addActivity")
    public @ResponseBody String addActivityTest(){
        try {
            activityService.save(new Activity("谷歌编程一小时", "济事楼", Date.from(Instant.now()), "123456", 10, "http://127.0.0.1/tongxinyun/D,jpg", "屌得一逼"));
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/pythontest")
    public String pythonTest(){
        try{
            if(this.encryptionService.checkIdentity("1452716", "852147")){
                return "true";
            }else{
                return "false";
            }

        }catch(Exception e){
            return "bbb";
        }
    }


}
