package com.example.service.repository;

import com.example.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/11/7.
 */
@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long>{

    //find ways
    Set<Apply>  findByMId(String id);
    Set<Apply>  findByMFromId(String fromId);
    Set<Apply>  findByMToId(String toId);
}
