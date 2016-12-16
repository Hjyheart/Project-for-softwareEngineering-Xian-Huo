package com.example.controller.tongxinyun;

import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.ClubService;
import com.example.service.CommentService;
import com.example.service.StudentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.List;

import static com.example.controller.tongxinyun.SessionController.getUsername;
import static com.example.controller.tongxinyun.SessionController.vertifySession;

/**
 * Created by hongjiayong on 2016/10/29.
 */

@Controller
@RequestMapping("/t_organize")
public class TOrganizeController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("")
    // 返回社团合集
    public String organize(ModelMap map, HttpServletRequest request) throws Exception {

        List<Club> clubList = clubService.findAll();

        map.addAttribute("organizes", clubList);

        return "tongxinyun/organizes";
    }

    @RequestMapping("/refresh")
    public String refresh(ModelMap map) throws Exception {

        List<Club> clubList = clubService.findAll();

        map.addAttribute("organizes", clubList);

        return "fragments :: organizeList";
    }

    @RequestMapping(value = "/{mId}")
    public String showOrganize(@PathVariable Long mId, ModelMap map, HttpServletRequest request){

        try {
            List<Club> clubList = clubService.findByMId(mId);
            List<Comment> commentList = clubList.iterator().next().getComments();

            if (commentList.size() >= 10){
                for (Comment comment:commentList.subList(0 ,9)){
                    comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
                }
                map.addAttribute("comments", commentList.subList(0, 9));
                map.addAttribute("lastIndex", 10);
            }else{
                for (Comment comment:commentList){
                    comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
                }
                map.addAttribute("comments", commentList);
                map.addAttribute("lastIndex", commentList.size());
            }

            map.addAttribute("organize", clubList.iterator().next());

            return "tongxinyun/organize";
        }catch (NullPointerException e){
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/{mId}/apply")
    // 申请参加社团
    public @ResponseBody String applyOrganize(@PathVariable Long mId, ModelMap map, HttpServletRequest request){
        try{
            List<Club> clubList = clubService.findByMId(mId);
            List<Student> studentList = studentService.findByMId(getUsername(request));

            for (Club club : clubList){
                if (club.getmId() == mId){
                    return "false";
                }
            }

            clubService.studentApplyClub(mId, getUsername(request));
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping(value = "/{mId}/unapply")
    // 取消申请社团
    public @ResponseBody String unApplyActivity(@PathVariable Long mId, ModelMap map, HttpServletRequest request){

        try{
            clubService.studentQuitClub(mId, getUsername(request));
            return "true";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/{mId}/comment", method = RequestMethod.GET)
    // 进行评论
    public @ResponseBody String commentOrganize(@PathVariable Long mId, String comment, HttpServletRequest request) throws Exception {

        if(!vertifySession(request)){
            return "login";
        }
        Comment newComment = new Comment(getUsername(request), mId, 0, comment, java.util.Date.from(Instant.now()), getUsername(request));
        clubService.addCommentToClub(newComment, mId);

        return "true";
    }

    @RequestMapping(value = "/{mId}/comment/refresh", method = RequestMethod.GET)
    // 异步加载评论
    public String commentRefresh(@PathVariable Long mId, int start, int number, ModelMap map) throws Exception {

        List<Comment> commentList = commentService.findAllCommentOfClub(mId);

        if (start + number <= commentList.size()){
            for (Comment comment:commentList.subList(start , number + start)){
                comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
            }
            map.addAttribute("comments", commentList.subList(start, number + start));
        }else if(start <= commentList.size()) {
            for (Comment comment:commentList.subList(start ,commentList.size())){
                comment.setStudentName(studentService.findByMId(comment.getmStudentId()).iterator().next().getmName());
            }
            map.addAttribute("comments", commentList.subList(start, commentList.size()));
        }else{
            map.addAttribute("comments", null);
        }
        return "fragments :: commentList";
    }
}

