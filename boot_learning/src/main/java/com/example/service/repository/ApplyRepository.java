package com.example.service.repository;

import com.example.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by deado on 2016/11/7.
 */
@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long>{

    //find ways
    List<Apply> findByMId(Long id);
    List<Apply> findByMClubId(Long id);

    @Modifying
    @Query("update Apply a set a.mAccept=?1 where a.mId=?2")
    int setApplyAcceptById(boolean accept, Long id);

}
