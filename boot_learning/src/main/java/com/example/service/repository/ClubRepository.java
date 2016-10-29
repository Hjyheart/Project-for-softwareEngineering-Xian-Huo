package com.example.service.repository;


import com.example.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    //find data ways
    Set<Club> findByMId(String Id);
    Set<Club> findByMName(String Name);
    Set<Club> findByMTeacher(String TeacherId);
    Set<Club> findByMChairmanId(String ChairmanId);

    //modifying ways

    @Modifying
    @Query("update Club c set c.mName=?1 where c.mId=?2")
    int setClubName(String NewName, String Id);

    @Modifying
    @Query("update Club c set c.mChairmanId=?1 where c.mId=?2")
    int setClubChairman(String NewChaiman ,String Id);

    @Modifying
    @Query("update Club c set c.mMemberNumber=c.mMemberNumber+1 where c.mId=?2")
    int setClubMemberNumberById(String Id);

    @Modifying
    @Query("update Club c set c.mTeacher=?1 where c.mId=?2")
    int setClubTeacherById(String Id);
}
