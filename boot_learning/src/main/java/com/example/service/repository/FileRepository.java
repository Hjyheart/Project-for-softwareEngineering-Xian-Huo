package com.example.service.repository;

import com.example.entity.File;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface FileRepository extends JpaRepository<File, String> {

    //find ways
    Set<File> findByID(String Id);
    Set<File> findByNAME(String Name);
    Set<File> findByCLUB(String CLUBID);
    Set<File> findByURL(String Url);

    //delete
    @Query("delete from FILE f where f.ID=?1")
    int deleteFileById(String Id);

    @Query("delete from FILE f where f.URL=?1")
    int deleteFileByUrl(String Url);


}
