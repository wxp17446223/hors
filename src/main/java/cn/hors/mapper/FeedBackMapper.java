package cn.hors.mapper;

import cn.hors.bean.FeedBack;

public interface FeedBackMapper {
    FeedBack findByUsId(Integer userId);

    boolean insertFeed(FeedBack record);

    boolean updateFeed(FeedBack record);
}