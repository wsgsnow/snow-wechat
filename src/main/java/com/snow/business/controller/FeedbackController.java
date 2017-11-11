package com.snow.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snow.business.entity.Feedback;
import com.snow.business.entity.result.ExceptionMsg;
import com.snow.business.entity.result.Response;
import com.snow.business.service.FeedbackService;

/**
 * Created by chenzhimin on 2017/2/23.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController{
    @Autowired
    private FeedbackService feedbackService;

    /**
     * @author chenzhimin
     * @date 2017年1月23日
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public Response save(Feedback feedback) {
        try {
        feedbackService.saveFeeddback(feedback,getUserId());
        } catch (Exception e) {
            logger.error("feedback failed, ", e);
            return result(ExceptionMsg.FAILED);
        }
        return result();
    }
}
