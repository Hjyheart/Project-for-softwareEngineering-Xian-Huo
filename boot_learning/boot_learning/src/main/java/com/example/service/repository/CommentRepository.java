package com.example.service.repository;

import com.example.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by deado on 2016/10/24.
 */
public interface CommentRepository extends CrudRepository<Comment, Long>{
}
