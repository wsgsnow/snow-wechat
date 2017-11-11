package com.snow.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snow.business.entity.Feedback;

/**
 * Created by chenzhimin on 2017/2/23.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
