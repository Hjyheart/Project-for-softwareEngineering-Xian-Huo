package com.example.controller.web;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/22.
 */
@Controller
@RequestMapping("/organizeCharge")
public class OrganizeChargeController {

    @RequestMapping("/basic")
    // 查看社团联管理员的基本信息
    public String basic(ModelMap map, HttpServletRequest request) {
        map.addAttribute("name", "basic");

        return "web/home";
    }

    @RequestMapping("/information")
    // 所有社团的列表 直接把社团简要信息加载到前端好了
    public String information(ModelMap map, HttpServletRequest request) {
        map.addAttribute("name", "information");

        return "web/home";
    }

    @RequestMapping("/exam/register")
    // 审核社团负责人的注册页
    public String examRegister(ModelMap map) {
        map.addAttribute("name", "exam register");

        return "web/home";
    }

    @RequestMapping("/exam/register/{name}/agree")
    // 同意某人的注册
    public
    @ResponseBody
    String agreeRegister(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/register/{name}/disagree")
    // 不同意某人的注册
    public
    @ResponseBody
    String disAgreeRegister(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/classroom")
    // 审核教室的申请
    public String examClassroom(ModelMap map) {
        map.addAttribute("name", "exam classroom");

        return "web/home";
    }

    @RequestMapping("/exam/classroom/{name}/agree")
    // 同意某人的教室申请
    public
    @ResponseBody
    String agreeClassroom(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/classroom/{name}/disagree")
    // 不同意某人的教室申请
    public
    @ResponseBody
    String disAgreeClassroom(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/poster")
    // 审核海报的张贴
    public String examPoster(ModelMap map) {
        map.addAttribute("name", "exam poster");

        return "web/home";
    }

    @RequestMapping("/exam/poster/{name}/agree")
    // 同意某人的海报申请
    public
    @ResponseBody
    String agreePoster(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/poster/{name}/disagree")
    // 不同意某人的海报申请
    public
    @ResponseBody
    String disAgreePoster(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/ground")
    // 审核活动场地
    public String examGround(ModelMap map) {
        map.addAttribute("name", "exam ground");

        return "web/home";
    }

    @RequestMapping("/exam/ground/{name}/agree")
    // 同意某人的场地申请
    public
    @ResponseBody
    String agreeGround(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/exam/ground/{name}/disagree")
    // 不同意某人的场地申请
    public
    @ResponseBody
    String disAgreeGround(@PathVariable String name, HttpServletRequest request) {
        System.out.println(name);

        return "true";
    }
}