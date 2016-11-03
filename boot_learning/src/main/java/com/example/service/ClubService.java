package com.example.service;

import com.example.entity.Club;
import com.example.service.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by stanforxc on 2016/11/2.
 */


@Service
public class ClubService {
    @Autowired
    @Resource
    private ClubRepository clubRepository;

    @Transactional
    public void save(Club club){
        clubRepository.save(club);
    }

    public Set<Club> findByMId(String id){
        return clubRepository.findByMId(id);
    }


}
