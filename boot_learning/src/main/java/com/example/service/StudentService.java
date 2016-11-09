package com.example.service;

import com.example.entity.*;
import com.example.service.repository.ApplyRepository;
import com.example.service.repository.StudentRepository;
import com.example.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by deado on 2016/10/23.
 */
@Transactional
@Service
public class StudentService {

    @Autowired
    @Resource
    private StudentRepository studentRepository;

    @Resource
    private ApplyRepository applyRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private EncryptionService encryptionService;

    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> findByMId(String id){
        return studentRepository.findByMId(id);
    }  //获取学生基本信息


    public void addStudent(String Id, String Name, String Grade, String Major,
                                String Contact,String Password) throws Exception {
        //TODO:encrypt passwords


        try{
            Student student= new Student(Id, Password, Name,Grade,Major,Contact);
            studentRepository.save(student);
        }catch(Exception e){
            throw e;
        }




    }

    public List<Club> getStudentClub(String id){
        return studentRepository.getStudentClub(id);
    }

    public List<Activity> getStudentActivity(String id){
        return studentRepository.getStudentActivity(id);
    }


    //
    public List<Apply> getAllSendApplies(String studentId) throws Exception{
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


    public List<Apply> getAllReceiveApplies(String studentId) throws Exception{
        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            return student.getReceiveApplies();
        }catch(Exception e){
            throw e;
        }
    }


    public List<Activity> getAllFavouriteActivities(String studentId) throws Exception{
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
}
