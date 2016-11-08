package com.example.service;

<<<<<<< HEAD
import com.example.entity.Teacher;
=======
import com.example.entity.Apply;
import com.example.entity.Teacher;
import com.example.service.repository.ApplyRepository;
>>>>>>> Hjyheart
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
<<<<<<< HEAD
    @Autowired
    @Resource
    private TeacherRepository teacherRepository;

=======

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private ApplyRepository applyRepository;


>>>>>>> Hjyheart
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
<<<<<<< HEAD
=======

    @Transactional
    public Set<Apply> getAllApplies(String teacherId) throws Exception{
        try{
            Teacher teacher = this.teacherRepository.findByMId(teacherId).iterator().next();
            return teacher.getApplies();
        }catch(Exception e){
            throw e;
        }
    }

>>>>>>> Hjyheart
}
