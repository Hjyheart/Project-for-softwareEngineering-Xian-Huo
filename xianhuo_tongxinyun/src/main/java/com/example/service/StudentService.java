package com.example.service;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Student;
import com.example.service.repository.StudentRepository;
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
@Service
public class StudentService {

    @Autowired
    @Resource
    private StudentRepository studentRepository;


    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }

    public Set<Student> findByMId(String id){
        return studentRepository.findByMId(id);
    }  //获取学生基本信息

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


}
