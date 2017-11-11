package com.snow.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.snow.business.entity.Letter;
import com.snow.business.entity.User;
import com.snow.business.entity.enums.LetterType;
import com.snow.business.entity.view.LetterSummary;
import com.snow.business.entity.view.LetterView;
import com.snow.business.repository.LetterRepository;
import com.snow.business.repository.UserRepository;
import com.snow.business.service.LetterService;
import com.snow.business.service.NoticeService;
import com.snow.system.utils.DateUtils;
import com.snow.system.utils.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DingYS on 2017/3/8.
 */
@Service
public class LetterServiceImpl implements LetterService{

    @Autowired
    private LetterRepository letterRepository;
    @Resource
    private NoticeService noticeService;
    @Autowired
    private UserRepository userRepository;

    /**
     * 发送私信
     * @param letter
     */
    public void sendLetter(Letter letter){
        if("original".equals(letter.getSendType())){
            letter.setType(LetterType.ORIGINAL);
        }else{
            letter.setType(LetterType.REPLY);
            List<String> userNameList = StringUtil.getAtUser(letter.getContent());
            if(null != userNameList && userNameList.size() > 0){
                User receiveUser = userRepository.findByUserName(userNameList.get(0));
                if(null != receiveUser){
                    letter.setReceiveUserId(receiveUser.getId());
                }
                String content = letter.getContent().substring(0,letter.getContent().indexOf("@"));
                if(StringUtils.isBlank(content)){
                    content = letter.getContent().substring(letter.getContent().indexOf("@")+receiveUser.getUserName().length()+1,letter.getContent().length());
                    letter.setContent(content);
                }
            }
        }
        letter.setCreateTime(DateUtils.getCurrentTime());
        letterRepository.save(letter);
        if(null == letter.getPid()){
            letter.setPid(letter.getId());
            letterRepository.updatePidById(letter.getId(),letter.getId());
        }
        // 添加消息通知
        noticeService.saveNotice(null,"letter",letter.getReceiveUserId(),String.valueOf(letter.getId()));
    }

    /**
     * 私信信息查询
     * @param userId
     * @param pageable
     * @return
     */
    public List<LetterSummary> findLetter(Long userId, Pageable pageable){
        List<LetterView> viewList = letterRepository.findLetterByReceiveUserId(userId,pageable);
        List<LetterSummary> summaryList = new ArrayList<LetterSummary>();
        for(LetterView view : viewList){
            LetterSummary summary = new LetterSummary(view);
            summaryList.add(summary);
        }
        return summaryList;
    }


}
