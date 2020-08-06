package cn.hors.service.impl;

import cn.hors.bean.FeedBack;
import cn.hors.mapper.FeedBackMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.service.FeedBackService;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Resource
    private FeedBackMapper feedbackMapper;

    @Override
    public List<FeedBack> findByUsId(Integer userId) {
        return feedbackMapper.findByUsId(userId);
    }


    @Override
    public List<FeedBack> findAll(FeedBack feedBack) {
        return feedbackMapper.findAll(feedBack);
    }

    @Override
    public boolean insert(FeedBack feedBack) {
        return feedbackMapper.insert(feedBack)>0?true:false;
    }

    @Override
    public boolean update(FeedBack feedBack) {
        return feedbackMapper.update(feedBack)>0?true:false;
    }

    @Override
    public boolean deleteByIds(Integer... ids) {
        return feedbackMapper.deleteByIds(ids)>0?true:false;
    }
}
