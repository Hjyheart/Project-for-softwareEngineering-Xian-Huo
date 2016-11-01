package com.example.controller;

import com.example.entity.Comment;
import com.example.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by deado on 2016/10/23.
 */
@RestController
@RequestMapping("/Test")
public class TestController {

    @Autowired
    ActivityService activityService;

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
}
