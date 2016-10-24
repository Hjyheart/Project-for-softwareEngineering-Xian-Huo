package com.example.service;

import com.example.entity.Club;
import com.example.service.repository.ClubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/10/23.
 */
@Service
public class ClubService {
    @Resource
    private ClubRepository clubRepository;

    @Transactional
    public void save(Club club){
        clubRepository.save(club);
    }
}
