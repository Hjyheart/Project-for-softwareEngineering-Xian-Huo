package com.example.service;

import com.example.entity.Teacher;
import com.example.service.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/10/23.
 */
@Service
public class TeacherService {
    @Resource
    private TeacherRepository teacherRepository;

    @Transactional
    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }
}
