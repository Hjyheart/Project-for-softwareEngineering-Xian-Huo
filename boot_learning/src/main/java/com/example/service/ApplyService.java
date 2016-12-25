package com.example.service;

import com.example.entity.Apply;
import com.example.service.repository.ApplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/11/7.
 */

@Service
public class ApplyService {
    @Resource
    private ApplyRepository applyRepository;

    @Transactional
    public void save(Apply apply){
        applyRepository.save(apply);
    }
}
