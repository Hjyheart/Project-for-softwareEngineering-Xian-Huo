package com.example.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/22.
 */
@Controller
@RequestMapping("/charge")
public class ChargeController {

    @RequestMapping("/basic")
    // 显示社团负责人的基本信息
    public String basic(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "charge basic");

        return "home";
    }

    @RequestMapping("/mycomments")
    // 显示用户的评论列表 支持跳转到该活动
    public String myComments(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "comments");

        return "home";
    }

    @RequestMapping(value = "/deletecomment")
    // 删除评论
    public @ResponseBody
    String deleteComment(Long id, HttpServletRequest request){
        System.out.println(id);

        return "true";
    }

    @RequestMapping("/myactivities")
    // 显示用户参加的活动列表 支持跳转到该活动
    public String myActivities(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "myActivities");

        return "home";
    }

    @RequestMapping("/myFavActivity")
    // 显示用户收藏的活动列表
    public String myFavActivities(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "myFavActivities");

        return "home";
    }

    @RequestMapping("/myorganizes")
    // 显示用户加入的社团列表 点击之后重定向到该社团
    public String myOrganizes(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "organizes");

        return "home";
    }

    /*
    *  以下为社团管理部分
    * */

    @RequestMapping("/myorganize")
    // 显示拥有的社团主页
    public String myOrganize(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "my organize");

        return "home";
    }

    @RequestMapping("/myorganize/apply/poster")
    // 申请海报张贴地点
    public String applyForPoster(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "apply for poster");

        return "home";
    }

    @RequestMapping(value = "/myorganize/apply/dealposter", method = RequestMethod.POST)
    // 处理海报申请表单
    public @ResponseBody String dealPoster(HttpServletRequest request){
        return "true";
    }

    @RequestMapping("/myorganize/apply/classroom")
    // 申请教室
    public String applyForClassroom(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "apply for classroom");

        return "home";
    }

    @RequestMapping(value = "/myorganize/apply/dealclassroom", method = RequestMethod.POST)
    // 处理教室申请表单
    public @ResponseBody String dealClassroom(HttpServletRequest request){
        return "true";
    }

    @RequestMapping("myorganize/apply/ground")
    // 申请场地
    public String applyForGround(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "apply for ground");

        return "home";
    }

    @RequestMapping(value = "/myorganize/apply/dealground", method = RequestMethod.POST)
    // 处理场地申请表单
    public @ResponseBody String dealGround(HttpServletRequest request){
        return "true";
    }

    @RequestMapping("/myorganize/edit")
    // 修改社团信息页
    public String edit(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "edit");

        return "home";
    }

    @RequestMapping("/myorganize/edit/addpic")
    // 加图片
    public @ResponseBody String addPic(HttpServletRequest request){
        return "true";
    }

    @RequestMapping("/myorganize/edit/addothers")
    // 加其他的
    public @ResponseBody String addOthers(HttpServletRequest request){
        return "true";
    }

    @RequestMapping("/myorganize/exam")
    // 审核参加社团的学生 打出学生列表 把学生基本信息加载进去
    public String examStu(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "exam list");

        return "home";
    }

    @RequestMapping(value = "/myorganize/exam/{name}/agree")
    // 同意某学生加入社团
    public @ResponseBody String agreeAplay(@PathVariable String name, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping(value = "/myorganize/exam/{name}/disagree")
    // 拒绝某学生加入社团
    public @ResponseBody String disAgreeAplay(@PathVariable String name, HttpServletRequest request){
        System.out.println(name);

        return "true";
    }

    @RequestMapping("/myorganize/inform")
    // 发送短信通知的页面
    public String inform(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "inform");

        return "home";
    }

    @RequestMapping(value = "/myorganize/informcontent", method = RequestMethod.POST)
    // 处理发表的内容
    public @ResponseBody String dealInform(HttpServletRequest request){
        return "true";
    }

    /* 活动管理 */

    @RequestMapping("myorganize/activity")
    // 自己社团的活动 打列表
    public String activity(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "my activity");

        return "home";
    }

    @RequestMapping("/myorganize/createactivity")
    // 生成活动页面
    public String createActivity(ModelMap map, HttpServletRequest request){
        map.addAttribute("name", "create activity");

        return "home";
    }

    @RequestMapping(value = "/myorganize/createactivity/out", method = RequestMethod.POST)
    // 发布活动
    public @ResponseBody String outActivity(HttpServletRequest request){
        return "true";
    }

    @RequestMapping(value = "myorganize/editactivity", method = RequestMethod.POST)
    public @ResponseBody String editActivity(HttpServletRequest request){
        return "true";
    }

}
