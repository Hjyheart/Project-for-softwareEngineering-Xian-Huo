package com.example.controller.tongxinyun;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

/**
 * Created by hongjiayong on 2016/11/7.
 */
public class SessionController {

    public static boolean vertifySession(HttpServletRequest request){
        String username;

        try{
            username = request.getSession().getAttribute("username").toString();
            return true;
        }catch (NullPointerException e){
            System.out.println("没有登录");
            return false;
        }
    }

    public static void setMySession(HttpServletRequest request, String newName){
        request.getSession().setAttribute("username", newName);
        request.getSession().setMaxInactiveInterval(60 * 60);
    }

    public static String getUsername(HttpServletRequest request){
        String username;

        try{
            username = request.getSession().getAttribute("username").toString();
            return username;
        }catch (NullPointerException e){
            System.out.println("没有登录");
            return "null";
        }
    }

}
