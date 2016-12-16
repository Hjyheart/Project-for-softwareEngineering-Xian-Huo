package com.example.service.repository;


import com.example.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    //find data ways
    List<Club> findByMId(Long Id);
    List<Club> findByMName(String Name);
    List<Club> findByMTeacher(String TeacherId);
    List<Club> findByMChairmanId(String ChairmanId);



    //modifying ways
    @Modifying
    @Query("update Club c set c.mName=?1 where c.mId=?2")
    int setClubName(String NewName, Long Id);

    @Modifying
    @Query("update Club c set c.mChairmanId=?1 where c.mId=?2")
    int setClubChairman(String NewChaiman ,Long Id);

    @Modifying
    @Query("update Club c set c.mMemberNumber=c.mMemberNumber+1 where c.mId=?1")
    int setAClubMemberNumberById(Long Id);


    @Modifying
    @Query("update Club c set c.mMemberNumber=c.mMemberNumber-1 where c.mId=?1")
    int setDClubMemberNumberById(Long Id);


    @Modifying
    @Query("update Club c set c.mTeacher=?1 where c.mId=?2")
    int setClubTeacherById(Long Id);

    @Modifying
    @Transactional
    @Query("update Club c set c.mMemberNumber=c.mMemberNumber-1 where c.mId=?1")
    int decreaseNumtFromClub(Long Id);


}
