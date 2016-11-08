package com.example.service;

<<<<<<< HEAD
import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Student;
=======
import com.example.entity.*;
import com.example.service.repository.ApplyRepository;
>>>>>>> Hjyheart
import com.example.service.repository.StudentRepository;
import com.example.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by deado on 2016/10/23.
 */
@Transactional
@Service
public class StudentService {

    @Autowired
    @Resource
    private StudentRepository studentRepository;

<<<<<<< HEAD
=======
    @Resource
    private ApplyRepository applyRepository;

    @Resource
    private TeacherRepository teacherRepository;

>>>>>>> Hjyheart

    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }

    public Set<Student> findByMId(String id){
        return studentRepository.findByMId(id);
    }  //获取学生基本信息
<<<<<<< HEAD
=======

>>>>>>> Hjyheart

    public void setPersonalInfo(String mId, String mName, String mGrade, String mMajor, String mCcontact,String password){
        Student student= new Student(mId,mName,mGrade,mMajor,mCcontact,password);
        studentRepository.save(student);
    }

    public List<Club> getStudentClub(String id){
        return studentRepository.getStudentClub(id);
    }

    public List<Activity> getStudentActivity(String id){
        return studentRepository.getStudentActivity(id);
    }


<<<<<<< HEAD
=======
    //
    public Set<Apply> getAllSendApplies(String studentId) throws Exception{
        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            return student.getSendApplies();
        }catch(Exception e){
            throw e;
        }
    }

    public void addSendApply(String studentId, String toId, Integer type, String description, Boolean teacher) throws Exception{
        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            Apply   apply = new Apply(studentId, toId, type, description);
            student.getSendApplies().add(apply);

            if(teacher){
                this.teacherRepository.findByMId(toId).iterator().next().getApplies().add(apply);
            }
            else{
                this.studentRepository.findByMId(toId).iterator().next().getReceiveApplies().add(apply);
            }

        }catch(Exception e){
            throw e;
        }
    }


    public Set<Apply> getAllReceiveApplies(String studentId) throws Exception{
        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            return student.getReceiveApplies();
        }catch(Exception e){
            throw e;
        }
    }


    public Set<Activity> getAllFavouriteActivities(String studentId) throws Exception{
        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            return student.getFavouriteactivities();
        }catch(Exception e){
            throw e;
        }
    }

    public void addFavouriteActivity(String studentId, Activity activity) throws Exception{
        try{
            Student student = this.findByMId(studentId).iterator().next();
            student.getFavouriteactivities().add(activity);
        }catch(Exception e){
            throw e;
        }
    }
>>>>>>> Hjyheart
}
