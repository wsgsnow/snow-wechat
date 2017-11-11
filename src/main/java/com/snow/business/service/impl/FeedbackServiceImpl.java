package com.snow.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snow.business.entity.Feedback;
import com.snow.business.repository.FeedbackRepository;
import com.snow.business.service.FeedbackService;
import com.snow.system.utils.DateUtils;

/**
 * Created by chenzhimin on 2017/2/23.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void saveFeeddback(Feedback feedback,Long userId) {
        feedback.setUserId(userId == null || userId == 0L ? null : userId);
        feedback.setCreateTime(DateUtils.getCurrentTime());
        feedback.setLastModifyTime(DateUtils.getCurrentTime());
        feedbackRepository.save(feedback);
    }
}
