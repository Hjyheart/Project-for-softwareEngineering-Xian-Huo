package com.example.service.repository;

import com.example.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by deado on 2016/10/24.
 */
public interface CommentRepository extends JpaRepository<Comment, String> {

    //find ways
    List<Comment> findByMStudentId(String Id);
    List<Comment>  findByMTargetId(Long Id);
    List<Comment>  findByMDate(Date date);
    List<Comment>  findByMId(Long Id);

    //modifying
    @Modifying
    @Query("update Comment c set c.mContent=?1 where c.mId=?2")
    int setCommentContentById(String NewContent , Long Id);


}
