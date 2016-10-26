package com.example.service.repository;

import com.example.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    //find
    Set<Activity> findByNAME(String Name);
    Set<Activity> findByID(String Id);
    Set<Activity> findByLOCATION(String Location);
    Set<Activity> findByTIME(Date Time);



    //modifying
    @Modifying
    @Query("update ACTIVITY a set a.NAME=?1 where a.ID=?2")
    int setActivityNameById(String NewName, String Id);

    @Modifying
    @Query("update ACTIVITY a set a.LOCATION=?1 where a.ID=?2")
    int setActivityLocationById(String NewLocation, String Id);

    @Modifying
    @Query("update ACTIVITY a set a.TIME=?1 where a.ID=?2")
    int setActivityDateById(String NewTime, String Id);

    @Modifying
    @Query("update ACTIVITY a set a.CONTACT =?1 where a.ID=?2")
    int setActivityContactById(String NewContact, String Id);


    @Modifying
    @Query("update ACTIVITY a set a.PRAISE = a.PRAISE + 1 where a.ID=?1")
    int addOneActivityPraiseById(String Id);


}
