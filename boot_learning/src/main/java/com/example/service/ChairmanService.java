package com.example.service;

/**
 * Created by deado on 2016/11/2.
 */


import com.example.entity.Club;
import com.example.entity.Student;
import com.example.service.repository.ClubRepository;
import com.example.service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ChairmanService
{
    @Resource
    private ClubRepository clubRepository;
    @Resource
    private StudentRepository studentRepository;


    public List<Club> getAllClubsOfOneStudent(String studentId) throws Exception {
        Student student = this.studentRepository.findByMId(studentId).iterator().next();

        //not found
        if (null == student) {
            throw new Exception();
        }

        return student.getClubs();
    }

}
