package com.example.service.repository;
<<<<<<< HEAD
=======

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Comment;
>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
/**
 * Created by deado on 2016/10/22.
 */

@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student, String> {
    //query
    Set<Student> findByMName(String name);

    Set<Student> findByMId(String id);

    Set<Student> findDistinctStudentByMMajor(String Major);



    //modifying
    @Modifying
    @Query("update Student s set s.mName=?1 where s.mId =?2")
    int setStudentNameById(String NewName, String Id);

    @Modifying
    @Query("update Student s set s.mMajor=?1 where s.mId=?2")
    int setStudentMajorById(String NewMajor, String Id);

    @Modifying
    @Query("update Student s set s.mContact=?1 where s.mId=?2")
    int setStudentContactById(String NewContact, String Id);

    @Modifying
    @Query("update Student s set s.mGrade=?1 where s.mId=?2")
    int setStudentGradeById(String NewGrade, String Id);
<<<<<<< HEAD
=======


    @Query("select c from Student s join s.clubs c where s.mId = ?1 ")
    List<Club> getStudentClub(String id);

    @Query("select a from Student s join s.activities a where s.mId = ?1")
    List<Activity> getStudentActivity(String id);

>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
}
