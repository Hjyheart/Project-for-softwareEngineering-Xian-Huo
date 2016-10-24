package com.example.service;

import com.example.entity.Activity;
import com.example.service.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by deado on 2016/10/23.
 */
@Service
public class ActivityService {
    @Resource
    private ActivityRepository activityRepository;
    @Transactional
    public void save(Activity activity){
        activityRepository.save(activity);
    }
}
