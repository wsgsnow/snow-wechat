package com.snow.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snow.business.entity.view.IndexCollectorView;
import com.snow.business.repository.CollectorRepository;
import com.snow.business.repository.UserRepository;
import com.snow.business.service.CollectorService;

/**
 * @Description: 获取收藏家
 * @Auth: yuyang
 * @Date: 2017/1/19 14:14
 * @Version: 1.0
 **/
@Service
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * 获取收藏家
     * @return
     */
    @Override
    public IndexCollectorView getCollectors() {
        IndexCollectorView indexCollectorView = new IndexCollectorView();
        try {
            Long mostCollectUser = collectorRepository.getMostCollectUser();
            indexCollectorView.setMostCollectUser(userRepository.findById(mostCollectUser));
            Long mostFollowedUser = collectorRepository.getMostFollowedUser(mostCollectUser);
            indexCollectorView.setMostFollowedUser(userRepository.findById(mostFollowedUser));
            String notUserIds = mostCollectUser+","+mostFollowedUser;
            Long mostPraisedUser = collectorRepository.getMostPraisedUser(notUserIds);
            indexCollectorView.setMostPraisedUser(userRepository.findById(mostPraisedUser));
            notUserIds += ","+mostPraisedUser;
            Long mostCommentedUser = collectorRepository.getMostCommentedUser(notUserIds);
            indexCollectorView.setMostCommentedUser(userRepository.findById(mostCommentedUser));
            notUserIds += ","+ mostCommentedUser;
            Long mostPopularUser = collectorRepository.getMostPopularUser(notUserIds);
            indexCollectorView.setMostPopularUser(userRepository.findById(mostPopularUser));
            notUserIds += ","+ mostPopularUser;
            Long mostActiveUser = collectorRepository.getMostActiveUser(notUserIds);
            indexCollectorView.setMostActiveUser(userRepository.findById(mostActiveUser));
        }catch (Exception e){
            System.err.println(e);
        }

        return indexCollectorView;
    }
}