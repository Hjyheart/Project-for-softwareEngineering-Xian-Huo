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


    public void addStudent(String Id, String Name, String Grade, String Major, String Contact,String Password) throws Exception {
        try{
            Student student= new Student(Id, Password, Name, Grade, Major, Contact, "/E.jpg");
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

    public Integer login(String Id, String Password){
        try{
            List<Student> stu = this.studentRepository.findByMId(Id);
            if(0 == stu.size()){//there is no such a student in database
                if(this.encryptionService.checkIdentity(Id, Password)){
                    this.addStudent(Id, "unknown"," "," "," ",Password);
                    return 0;
                }
                return -1;//check fail
            }else{//this user has already been in database
                //check the password
                if (this.encryptionService.comparePW(Id,Password)){
                    return 1;
                }else{
                    return -1;
                }
            }

        }catch(Exception e){
            return -1;
        }
    }
}
