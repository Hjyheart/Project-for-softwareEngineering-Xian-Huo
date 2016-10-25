package com.example.service;

import com.example.entity.File;
import com.example.service.repository.FileRepository;
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
    public void save(File file){
        fileRepository.save(file);
    }
}
