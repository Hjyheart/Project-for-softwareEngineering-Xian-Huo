package com.example.service;

import com.example.entity.ClubFile;
import com.example.service.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by hongjiayong on 2016/12/21.
 */
@Transactional
@Service
public class FileService {
    @Autowired
    @Resource
    private FileRepository fileRepository;

    @Transactional
    public void save(ClubFile clubFile){
        fileRepository.save(clubFile);
    }

    @Transactional
    public void remove(ClubFile clubFile){
        fileRepository.delete(clubFile);
    }

    public ClubFile findByUrl(String url){
        return fileRepository.findByMUrl(url).iterator().next();
    }
}
