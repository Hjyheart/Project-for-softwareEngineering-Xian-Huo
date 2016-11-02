package com.example.service;

import com.example.entity.Teacher;
import com.example.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.SqlResultSetMapping;
import java.util.Set;

/**
 * Created by stanforxc on 2016/11/1.
 */

@Service
public class TeacherService {
    @Autowired
    @Resource
    private TeacherRepository teacherRepository;

    @Transactional
    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public Set<Teacher> findByMId(String id){
        return teacherRepository.findByMId(id);
    }

    public void setPersonalInfo(String mId, String mName, String mContact){
        Teacher teacher = new Teacher(mId, mName, mContact);
        teacherRepository.save(teacher);
    }
}
