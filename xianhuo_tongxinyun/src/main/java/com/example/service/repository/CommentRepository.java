package com.example.service.repository;

import com.example.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deado on 2016/10/24.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
