package com.example.service;

import com.example.entity.Apply;
import com.example.entity.Teacher;
import com.example.service.repository.ApplyRepository;
import com.example.service.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by stanforxc on 2016/11/1.
 */

@Service
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private ApplyRepository applyRepository;


    @Transactional
    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public List<Teacher> findByMId(String id){
        return teacherRepository.findByMId(id);
    }

    public void setPersonalInfo(String mId, String mName, String mContact){
        Teacher teacher = new Teacher(mId, mName, mContact);
        teacherRepository.save(teacher);
    }

    @Transactional
    public List<Apply> getAllApplies(String teacherId) throws Exception{
        try{
            Teacher teacher = this.teacherRepository.findByMId(teacherId).iterator().next();
            return teacher.getApplies();
        }catch(Exception e){
            throw e;
        }
    }

}
