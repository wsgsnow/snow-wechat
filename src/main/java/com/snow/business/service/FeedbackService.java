package com.snow.business.service;

import com.snow.business.entity.Feedback;

/**
 * Created by chenzhimin on 2017/2/23.
 */
public interface FeedbackService {

    public void saveFeeddback(Feedback feedback,Long userId);
}
