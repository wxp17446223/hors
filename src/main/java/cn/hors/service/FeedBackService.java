package cn.hors.service;

import cn.hors.bean.FeedBack;

public interface FeedBackService {

    FeedBack findByUsId(Integer userId);

    boolean insertFeed(FeedBack record);

    boolean updateFeed(FeedBack record);


}
