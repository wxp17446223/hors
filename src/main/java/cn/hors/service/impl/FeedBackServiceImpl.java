package cn.hors.service.impl;

import cn.hors.bean.Feedback;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.FeedbackMapper;
import cn.hors.service.FeedBackService;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public Feedback findByUsId(Integer userId) {
        return feedbackMapper.findByUsId(userId);
    }

    @Override
    public boolean insertFeed(Feedback record) {
        return feedbackMapper.insertFeed(record);
    }

    @Override
    public boolean updateFeed(Feedback record) {
        return feedbackMapper.updateFeed(record);
    }

    @Override
    public List<Feedback> findAll(Feedback feedBack) {
        return feedbackMapper.findAll(feedBack);
    }

    @Override
    public boolean insert(Feedback feedBack) {
        return feedbackMapper.insert(feedBack)>0?true:false;
    }

    @Override
    public boolean update(Feedback feedBack) {
        return feedbackMapper.update(feedBack)>0?true:false;
    }

    @Override
    public boolean deleteByIds(Integer... ids) {
        return feedbackMapper.deleteByIds(ids)>0?true:false;
    }
}
