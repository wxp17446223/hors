package cn.hors.mapper;

import cn.hors.bean.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackMapper {

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
    int insert(Feedback feedBack);

    /**
     * 修改反馈
     * @param feedBack
     * @return
     */
    int update(Feedback feedBack);

    /**
     * 删除反馈
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Integer... ids);
}
