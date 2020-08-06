package cn.hors.service;

import cn.hors.bean.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackService {

    Feedback findByUsId(Integer userId);

    boolean insertFeed(Feedback record);

    boolean updateFeed(Feedback record);

    /**
     * 查询所有反馈信息
     * @param feedBack 反馈bean
     * @return
     */
    List<Feedback> findAll(Feedback feedBack);

    /**
     * 新增反馈
     * @param feedBack
     * @return
     */
    boolean insert(Feedback feedBack);

    /**
     * 修改反馈
     * @param feedBack
     * @return
     */
    boolean update(Feedback feedBack);

    /**
     * 删除反馈
     * @param ids
     * @return
     */
    boolean deleteByIds(@Param("ids") Integer... ids);

}
