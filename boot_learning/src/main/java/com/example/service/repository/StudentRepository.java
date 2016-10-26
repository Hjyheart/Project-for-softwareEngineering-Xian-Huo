package com.example.service.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    //query
    Student findByName(String name);

    Student findById(String id);

    @Query("from STUDENT s where s.NAME=:name")
    Student findStudent(@Param("name") String name);

    Set<Student> findDistinctStudentByMAJOR(String Major);

    //modifying
    @Modifying
    @Query("update STUDENT s set s.NAME=?1 where s.ID =?2")
    int setStudentNameById(String NewName, String Id);

    @Modifying
    @Query("update STUDENT s set s.MAJOR=?1 where s.ID=?2")
    int setStudentMajorById(String NewMajor, String Id);

    @Modifying
    @Query("update STUDENT s set s.CONTACT=?1 where s.ID=?2")
    int setStudentContactById(String NewContact, String Id);

    @Modifying
    @Query("update STUDENT s set s.GRADE=?1 where s.ID=?2")
    int setStudentGradeById(String NewGrade, String Id);

}
