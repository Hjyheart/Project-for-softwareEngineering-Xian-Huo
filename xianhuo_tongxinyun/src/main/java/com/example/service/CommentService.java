package com.example.service;

import com.example.entity.Comment;
import com.example.service.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
