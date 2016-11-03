package com.example.service;

import com.example.entity.Activity;
import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.repository.ClubRepository;
import com.example.service.repository.StudentRepository;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by stanforxc on 2016/11/2.
 */


@Service
public class ClubService {
    @Autowired
    @Resource
    private ClubRepository clubRepository;
    @Autowired
    private StudentRepository studentRepository;



    @Transactional
    public void save(Club club){
        clubRepository.save(club);
    }

    public Set<Club> findByMId(String id){
        return clubRepository.findByMId(id);
    }

<<<<<<< HEAD

=======
    @Transactional
    public boolean studentQuitClub(String clubid,String stuid) throws Exception{  //AoQ申请true或者退出false

        try {
            Club c = clubRepository.findByMId(clubid).iterator().next();
            Student s = studentRepository.findByMId(stuid).iterator().next();
            Iterator<Student> iterStu = c.getStudents().iterator();
            Iterator<Club> iterClub = s.getClubs().iterator();
            while (iterClub.hasNext()){
                if (0 == iterClub.next().getmId().compareTo(clubid)) {
                    iterClub.remove();
                    break;
                }
            }

            while (iterStu.hasNext()){
                if (0 == iterStu.next().getmId().compareTo(stuid) ){
                    iterStu.remove();
                    break;
                }
            }
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Transactional
    public boolean studentApplyClub(String clubid,String stuid){
        Set<Club> clubs = clubRepository.findByMId(clubid);
        Set<Student> students = studentRepository.findByMId(stuid);
        Iterator<Club> iterClub= clubs.iterator();
        Iterator<Student> iterStudent = students.iterator();
        if(!iterStudent.hasNext() && !iterClub.hasNext()){
            return false;
        }else {
            Club c = iterClub.next(); Student s = iterStudent.next();

            c.getStudents().add(s);
            s.getClubs().add(c);
            clubRepository.setClubMemberNumberById(clubid);

            return true;
        }
    }

    @Transactional
    public Set<Comment> getClubComment(String clubid){
        Club club = clubRepository.findByMId(clubid).iterator().next();
       return club.getComments();

    }

>>>>>>> b442106143f961ee0da33bc742336fbfd5ac2960
}
