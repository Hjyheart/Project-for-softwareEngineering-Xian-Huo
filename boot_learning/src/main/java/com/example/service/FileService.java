package com.example.service;

import com.example.entity.ClubFile;
import com.example.service.repository.FileRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/10/23.
 */

@Service
public class FileService {
    @Resource
    private FileRepository fileRepository;
    @Transactional
    public void save(ClubFile file){
        fileRepository.save(file);
    }


}
