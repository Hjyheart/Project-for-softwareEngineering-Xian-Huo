package com.example.service.repository;

import com.example.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Set;

/**
 * Created by deado on 2016/10/24.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //find ways
    Set<Comment>  findBySTUDENTID(String Id);
    Set<Comment>  findByTARGETID(String Id);
    Set<Comment>  findByDATE(Date date);
    Set<Comment>  findByID(String Id);

    //modifying
    @Modifying
    @Query("update COMMENT c set c.CONTENT=?1 where c.ID=?2")
    int setCommentContentById(String NewContent , String Id);


}
