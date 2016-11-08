package com.example.service.repository;

import com.example.entity.ClubFile;
import com.example.entity.ClubFile;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface FileRepository extends JpaRepository<ClubFile, String> {

    //find ways
    List<ClubFile> findByMId(String Id);
    List<ClubFile> findByMName(String Name);
    List<ClubFile> findByMClub(String CLUBID);
    List<ClubFile> findByMUrl(String Url);

    //delete
    @Query("delete from ClubFile f where f.mId=?1")
    int deleteFileById(String Id);

    @Query("delete from ClubFile f where f.mUrl=?1")
    int deleteFileByUrl(String Url);
}
