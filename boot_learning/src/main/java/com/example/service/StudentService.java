package com.example.service;

import com.example.entity.Student;
import com.example.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

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
    }

    public void setPersonalInfo(String mId, String mName, String mGrade, String mMajor, String mCcontact,String password){
        Student student= new Student(mId,mName,mGrade,mMajor,mCcontact,password);
        studentRepository.save(student);
    }
}
