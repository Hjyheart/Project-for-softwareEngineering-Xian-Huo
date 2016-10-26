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
    Set<Teacher>  findByID(String Id);
    Set<Teacher>  findByNAME(String Name);

    //modifying
    @Modifying
    @Query("update TEACHER t set t.CONTACT=?1 where t.ID=?2")
    int setContactById(String NewContact, String Id);

    @Modifying
    @Query("update TEACHER t set t.NAME=?1 where t.ID=?2")
    int setNameById(String NewName, String Id);
}
