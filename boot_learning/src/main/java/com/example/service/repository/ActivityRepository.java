package com.example.service.repository;

import com.example.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    
    //find
    Set<Activity> findByMName(String Name);
    Set<Activity> findByMId(String Id);
    Set<Activity> findByMLocation(String Location);
    Set<Activity> findByMTime(Date Time);



    //modifying
    @Modifying
    @Query("update Activity a set a.mName=?1 where a.mId=?2")
    int setActivityNameById(String NewName, String Id);

    @Modifying
    @Query("update Activity a set a.mLocation=?1 where a.mId=?2")
    int setActivityLocationById(String NewLocation, String Id);

    @Modifying
    @Query("update Activity a set a.mTime=?1 where a.mId=?2")
    int setActivityDateById(String NewTime, String Id);

    @Modifying
    @Query("update Activity a set a.mContact =?1 where a.mId=?2")
    int setActivityContactById(String NewContact, String Id);


    @Modifying
    @Query("update Activity a set a.mPraise = a.mPraise + 1 where a.mId=?1")
    int addOneActivityPraiseById(String Id);


}
