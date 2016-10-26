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
    Set<Club> findByID(String Id);
    Set<Club> findByNAME(String Name);
    Set<Club> findByTEACHER(String TeacherId);
    Set<Club> findByCHARMANID(String ChairmanId);

    //modifying ways

    @Modifying
    @Query("update CLUB c set c.NAME=?1 where c.ID=?2")
    int setClubName(String NewName, String Id);

    @Modifying
    @Query("update CLUB c set c.CHAIRMANID=?1 where c.ID=?2")
    int setClubChairman(String NewChaiman ,String Id);

    @Modifying
    @Query("update CLUB c set c.MEMBER_NUMBER=c.MEMBER_NUMBER+1 where c.ID=?2")
    int setClubMemberNumberById(String Id);

    @Modifying
    @Query("update CLUB c set c.TEACHER=?1 where c.ID=?2")
    int setClubTeacherById(String Id);
}
