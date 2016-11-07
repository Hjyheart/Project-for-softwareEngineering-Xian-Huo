package com.example.service.repository;

import com.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    //find ways
    Set<Teacher>  findByMId(String Id);
    Set<Teacher>  findByMName(String Name);

    //modifying
    @Modifying
    @Query("update Teacher t set t.mContact=?1 where t.mId=?2")
    int setContactById(String NewContact, String Id);

    @Modifying
    @Query("update Teacher t set t.mName=?1 where t.mId=?2")
    int setNameById(String NewName, String Id);
}
