package com.example.controller.tongxinyun;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by hongjiayong on 2016/10/30.
 */
@Controller
@RequestMapping("/t_message")
public class TMessageController {

    @RequestMapping("")
    // 显示各社团发的信息
    public String message(ModelMap map, HttpServletRequest request){
        ArrayList<organize> organizesList = new ArrayList<>();

        ArrayList<THomeController.testUser> messageListOne = new ArrayList<>();
        messageListOne.add(new THomeController.testUser("洪嘉勇", 21, "菜鸡"));
        messageListOne.add(new THomeController.testUser("王依睿", 19, "强的不行不行的!"));
        organizesList.add(new organize(messageListOne, "羽毛球社"));

        ArrayList<THomeController.testUser> messageListTwo = new ArrayList<>();
        messageListTwo.add(new THomeController.testUser("洪嘉勇", 21, "菜鸡"));
        messageListTwo.add(new THomeController.testUser("王依睿", 19, "强的不行不行的!"));
        organizesList.add(new organize(messageListTwo, "篮球社"));

        map.addAttribute("messages", organizesList);

        return "tongxinyun/messages";
    }

    @RequestMapping("/refresh")
    // 刷新消息列表
    public String messageRefresh(ModelMap map, HttpServletRequest request){
        ArrayList<organize> organizesList = new ArrayList<>();

        ArrayList<THomeController.testUser> messageListOne = new ArrayList<>();
        messageListOne.add(new THomeController.testUser("洪嘉勇", 21, "菜鸡"));
        messageListOne.add(new THomeController.testUser("王依睿", 19, "强的不行不行的!"));
        organizesList.add(new organize(messageListOne, "乒乓球社"));

        ArrayList<THomeController.testUser> messageListTwo = new ArrayList<>();
        messageListTwo.add(new THomeController.testUser("洪嘉勇", 21, "菜鸡"));
        messageListTwo.add(new THomeController.testUser("王依睿", 19, "强的不行不行的!"));
        organizesList.add(new organize(messageListTwo, "足球社"));

        map.addAttribute("messages", organizesList);

        return "tongxinyun/messages :: messageList";
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody String jsonTest(@RequestBody String test){
//        for (String str:test){
//            System.out.println(str);
//        }
        System.out.println(test);
        return "true";
    }

    @RequestMapping("/bug")
    public String bug(){
        return "test";
    }


    public class organize{
        private ArrayList<THomeController.testUser> messageList;
        private String name;

        public organize(ArrayList<THomeController.testUser> messageList, String name){
            this.messageList = messageList;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public ArrayList<THomeController.testUser> getMessageList() {
            return messageList;
        }
    }
}
