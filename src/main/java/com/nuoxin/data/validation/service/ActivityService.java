package com.nuoxin.data.validation.service;

import com.nuoxin.data.validation.dao.eapp.ActivityRepository;
import com.nuoxin.data.validation.entity.eapp.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenggang on 1/13/18.
 *
 * @author fenggang
 * @date 1/13/18
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity findById(Long id){
        return activityRepository.findOne(id);
    }
}
