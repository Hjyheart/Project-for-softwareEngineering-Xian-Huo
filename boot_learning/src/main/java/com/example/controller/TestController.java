package com.example.controller;

import com.example.entity.Comment;
import com.example.service.ActivityService;
import com.example.service.StorageService;
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
