package com.example.service;

import com.example.entity.Comment;
import com.example.service.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by deado on 2016/10/24.
 */
@Service
public class CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findAllComment(String student_id){
        return commentRepository.findByMStudentId(student_id);
    }

    public List<Comment> findAllCommentOfActivity(Long activityId) throws Exception {
        try{
            return this.commentRepository.findByMTargetId(activityId);
        }catch(Exception e){
            throw e;
        }
    }
}
