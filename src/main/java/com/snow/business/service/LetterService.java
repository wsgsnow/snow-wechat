package com.snow.business.service;

import org.springframework.data.domain.Pageable;

import com.snow.business.entity.Letter;
import com.snow.business.entity.view.LetterSummary;

import java.util.List;

/**
 * Created by DingYS on 2017/3/8.
 */
public interface LetterService {

    public void sendLetter(Letter letter);

    public List<LetterSummary> findLetter(Long userId, Pageable pageable);
}
