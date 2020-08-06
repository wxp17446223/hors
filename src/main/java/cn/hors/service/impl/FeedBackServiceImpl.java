package cn.hors.service.impl;

import cn.hors.bean.FeedBack;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.FeedBackMapper;
import cn.hors.service.FeedBackService;
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Resource
    private FeedBackMapper feedbackMapper;

    @Override
    public FeedBack findByUsId(Integer userId) {
        return feedbackMapper.findByUsId(userId);
    }

    @Override
    public boolean insertFeed(FeedBack record) {
        return feedbackMapper.insertFeed(record);
    }

    @Override
    public boolean updateFeed(FeedBack record) {
        return feedbackMapper.updateFeed(record);
    }
}
