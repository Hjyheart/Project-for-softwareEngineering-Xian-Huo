package com.example.service.repository;


import com.example.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
