package com.example.service;

import com.example.entity.Student;
import com.example.service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/10/23.
 */
@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }
}
